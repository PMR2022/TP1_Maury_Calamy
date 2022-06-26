package com.example.tp1_maury_calamy

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1_maury_calamy.DataClass.AllData
import com.example.tp1_maury_calamy.DataClass.ListeToDo
import com.example.tp1_maury_calamy.DataClass.ProfilListeToDo
import com.google.gson.Gson
import java.io.File
import java.lang.Exception

class ChoixListActivity : AppCompatActivity() {

    private lateinit var user: ProfilListeToDo
    private lateinit var listeName: EditText
    private lateinit var newListe: ListeToDo
    private lateinit var listRecycl : RecyclerView
    private lateinit var listeData : AllData // Cette variable contient la liste de tous les profils. On s'en sert pour serializer/désérializer
    override fun onCreate(savedInstanceState: Bundle?) {
        listeData = deserialize() //lecture du fichier de données

        super.onCreate(savedInstanceState)
        setContentView(R.layout.choix_list_activity)

        listeName = findViewById(R.id.nouvelleList)
        val pseudo = intent.getStringExtra("pseudo").toString()

        user = getProfile(pseudo)


        var adapter = ListAdapter(dataSet = user.listActivite)
        listRecycl = findViewById<RecyclerView>(R.id.list) //création du recyclerView



        listRecycl.adapter = adapter
        listRecycl.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.setOnItemClickListener(object : ListAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                var nomListe = user.listActivite.get(position).titreListeToDo
                val showListActivity = Intent(this@ChoixListActivity,ShowListActivity::class.java)
                showListActivity.putExtra("liste", nomListe)
                showListActivity.putExtra("pseudo", pseudo)
                startActivity(showListActivity)
            }


        })

        val btnOk: Button = findViewById(R.id.btnOkNewList)
        btnOk.setOnClickListener {

            newListe = ListeToDo(listeName.text.toString(), ArrayList())
            user.listActivite.add(newListe)
            serialize()

            listRecycl.adapter!!.notifyDataSetChanged()

            //Toast.makeText(this, user.listActivite.toString(), Toast.LENGTH_LONG).show()
        }

    }

    private fun getProfile(pseudo: String): ProfilListeToDo {

        // Cette méthode permet d'extraire un profil de la liste de profils. Si le profil demandé n'existe pas, elle le crée
        var res : ProfilListeToDo
        res = ProfilListeToDo(pseudo, ArrayList())
        var found = false
        for (profil in listeData.listeProfils){
            if (profil.name == pseudo){
                found = true
                res = profil
            }
        }

        if(found == false){
            listeData.listeProfils.add(res)
        }
        return(res)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here.
        val id = item.getItemId()

        if (id == R.id.action_one) {
            val settingActivity = Intent(this, SettingActivity::class.java)
            startActivity(settingActivity)
            return true
        }

        return super.onOptionsItemSelected(item)

    }

    class ListAdapter(
        private val dataSet: ArrayList<ListeToDo>
    ) : RecyclerView.Adapter<ChoixListActivity.ListAdapter.ItemViewHolder>() {

        private lateinit var myListener : onItemClickListener

        interface onItemClickListener{
            fun onItemClick(position : Int)
        }

        fun setOnItemClickListener(listener : onItemClickListener){
            myListener = listener
        }

        override fun getItemCount(): Int = dataSet.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)

            return ItemViewHolder(itemView = itemView,myListener)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(list = dataSet[position])
        }


        class ItemViewHolder(itemView: View, listener : onItemClickListener) : RecyclerView.ViewHolder(itemView) {

            private val textListe = itemView.findViewById<TextView>(R.id.textListe)

            fun bind(list: ListeToDo) {
                textListe.text = list.titreListeToDo
            }

            init{
                itemView.setOnClickListener {
                    listener.onItemClick(adapterPosition)
                }
            }

        }
    }


    fun ecrireFichier(txt: String){
            File(this.filesDir, "save.txt").outputStream().use {
            it.write(txt.toByteArray())
        }
    }

    fun lireFichier(): String {
        var recuperation : String
        try {
            recuperation = File(this.filesDir, "save.txt").bufferedReader().readText();
        }
        catch(e: Exception){ // pour prévenir d'un crash quand le fichier de sauvegarde n'existe pas
            recuperation = "empty"
        }
        return(recuperation)
    }

    fun serialize(){
        val gson = Gson()
        var jsonString = gson.toJson(listeData)
        ecrireFichier(jsonString)
    }

    fun deserialize(): AllData {
        val data = lireFichier()
        val gson = Gson()
        var testModel : AllData
        if (data == "empty"){
            testModel = AllData(ArrayList())

        }
        else {
            testModel = gson.fromJson(data, AllData::class.java)
        }
        return (testModel)
    }
}

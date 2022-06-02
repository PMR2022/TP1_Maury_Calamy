package com.example.tp1_maury_calamy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChoixListActivity : AppCompatActivity() {
    private lateinit var user: User
    private lateinit var listeName: EditText
    private lateinit var newListe: Liste
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choix_list_activity)

        listeName = findViewById(R.id.nouvelleList)
        val pseudo = intent.getStringExtra("pseudo").toString()
        //Toast.makeText(this, pseudo, Toast.LENGTH_LONG).show() //test pseudo

        // TODO : récupérer le user à partir du pseudo et l'afficher, pour l'instant je me contente d'en créer un vierge
        user = User(pseudo, ArrayList())

        val btnOk: Button = findViewById(R.id.btnOkNewList)
        btnOk.setOnClickListener {

            newListe = Liste(listeName.text.toString(),ArrayList())
            user.listActivite.add(newListe)
            //Toast.makeText(this, user.listActivite.toString(), Toast.LENGTH_LONG).show()
        }

        // TODO : écouter les click sur les différentes listes et ouvrir ShowListActivity en lui passant le nom de la liste en argument

         /*         bout de code utile pour ça

          val showListActivity = Intent(this,ShowListActivity::class.java)
          choixListActivity.putExtra("liste", LeNomDeLaListeCliquée)
          startActivity(showListActivity)

        */
        val listRecycl = findViewById<RecyclerView>(R.id.list) //création du recyclerView
        listRecycl.adapter = ListAdapter(dataSet = provideDataSet())
        listRecycl.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

    }
    fun provideDataSet(): List<Liste> {
        val result = mutableListOf<Liste>()
        repeat(10) { intex ->
            val list = Liste(
                name = "Titre $intex",
                listItem = ArrayList(),
            )

            result.add(list)
        }
        Log.d("myActivity",result.size.toString())
        return result
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
        private val dataSet: List<Liste>
    ) : RecyclerView.Adapter<ChoixListActivity.ListAdapter.ItemViewHolder>() {

        override fun getItemCount(): Int = dataSet.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)

            return ItemViewHolder(itemView = itemView)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(list = dataSet[position])
        }


        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val textListe = itemView.findViewById<TextView>(R.id.textListe)

            fun bind(list: Liste) {
                textListe.text = list.name
            }

        }
    }
}

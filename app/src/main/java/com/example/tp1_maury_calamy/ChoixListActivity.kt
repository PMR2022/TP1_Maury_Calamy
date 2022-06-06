package com.example.tp1_maury_calamy

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ChoixListActivity : AppCompatActivity() {
    private lateinit var user: ProfilListeToDo
    private lateinit var listeName: EditText
    private lateinit var newListe: ListeToDo
    private lateinit var listRecycl : RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choix_list_activity)

        listeName = findViewById(R.id.nouvelleList)
        val pseudo = intent.getStringExtra("pseudo").toString()
        //Toast.makeText(this, pseudo, Toast.LENGTH_LONG).show() //test pseudo

        // TODO : récupérer le user à partir du pseudo et l'afficher, pour l'instant je me contente d'en créer un vierge
        user = ProfilListeToDo(pseudo, ArrayList())


        var adapter = ListAdapter(dataSet = user.listActivite)
        listRecycl = findViewById<RecyclerView>(R.id.list) //création du recyclerView



        listRecycl.adapter = adapter
        listRecycl.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        adapter.setOnItemClickListener(object : ListAdapter.onItemClickListener{
            override fun onItemClick(position: Int) {

                var nomListe = user.listActivite.get(position).titreListeToDo
                val showListActivity = Intent(this@ChoixListActivity,ShowListActivity::class.java)
                showListActivity.putExtra("liste", nomListe)
                startActivity(showListActivity)
            }


        })

        val btnOk: Button = findViewById(R.id.btnOkNewList)
        btnOk.setOnClickListener {

            newListe = ListeToDo(listeName.text.toString(), ArrayList())
            user.listActivite.add(newListe)

            listRecycl.adapter!!.notifyDataSetChanged()

            //Toast.makeText(this, user.listActivite.toString(), Toast.LENGTH_LONG).show()
        }

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
}

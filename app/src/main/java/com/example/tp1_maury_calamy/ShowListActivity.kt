package com.example.tp1_maury_calamy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1_maury_calamy.DataClass.ItemApi
import com.example.tp1_maury_calamy.DataClass.listItem
import com.google.gson.Gson
import kotlinx.coroutines.*

class ShowListActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.v("myActivity", "Arrivée dans ShowListActivity")
        val idListe = getIntent().getExtras()!!.getInt("idListe")
        val hash = getIntent().getExtras()!!.getString("hash")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)
        getItem(hash!!,idListe)


        val btnOk : Button = findViewById(R.id.btnOkNewItem)
        btnOk.setOnClickListener {
            var text : String =  findViewById<EditText>(R.id.indicItem).text.toString()
            addItem(hash,idListe,text)
            getItem(hash,idListe)
        }


        Log.v("myActivity", "onCreateFini")
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
            val settingActivity = Intent(this,SettingActivity::class.java)
            startActivity(settingActivity)
            return true
        }

        return super.onOptionsItemSelected(item)

    }



    class ItemAdapter(
        private val dataSet: listItem
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {



        override fun getItemCount(): Int {
            if(dataSet.listItem!=null) {return dataSet.listItem.size}
            return 0
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

            return ItemViewHolder(itemView = itemView)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(item = dataSet.listItem[position])
        }


        inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val textItem = itemView.findViewById<TextView>(R.id.textItem)
            private val checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)

            fun bind(item: ItemApi) {
                textItem.text = item.label
                checkBox.isChecked = item.checked
            }

        }
    }


    private val mainActivityScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    override fun onDestroy() {
        super.onDestroy()
        mainActivityScope.cancel()
    }

    private fun getItem(hash : String, idListe : Int){
        Log.v("myActivity","appel getItem")
        mainActivityScope.launch {
            val lists = DataProvider.getItems(hash,idListe)
            Log.v("myActivity",lists.toString())
            val list = findViewById<RecyclerView>(R.id.listItem)
            list.adapter = ItemAdapter(dataSet = lists)
            list.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
            Log.v("myActivity","RecyclerView Item créé")
        }
    }

    private fun addItem(hash : String,idListe:Int, text: String) {
            Log.v("myActivity","appel addItem")
        mainActivityScope.launch {
            if (text.toString()!=null) {
                val lists = DataProvider.addItem(hash,idListe, text)
                Log.v("myActivity","ajout Item")
            }
            else Log.v("myActivity","text vide")

        }
    }

}

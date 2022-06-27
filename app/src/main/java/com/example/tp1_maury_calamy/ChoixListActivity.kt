package com.example.tp1_maury_calamy

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.tp1_maury_calamy.DataClass.listApi
import com.example.tp1_maury_calamy.DataClass.listItem
import com.example.tp1_maury_calamy.DataClass.listList
import com.example.tp1_maury_calamy.db.DataProviderSql
import com.example.tp1_maury_calamy.db.dataTypes.Lists
import kotlinx.coroutines.*
import java.lang.Exception

class ChoixListActivity : AppCompatActivity() {


    private lateinit var listRecycl : RecyclerView
    private lateinit var hash : String
    private val myDataProvider : DataProvider2  by lazy { DataProvider2(this.application) }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.choix_list_activity)
        hash = intent.getStringExtra("hash").toString()
        val btnOk: Button = findViewById(R.id.btnOkNewList)

        val internetOk = checkInternet(this)
        if(!internetOk) btnOk.isEnabled = false


        getListe(hash)
        Log.v("myActivity", "api passé")
        //getItem1113(hash)



        btnOk.setOnClickListener {
            var text : String =  findViewById<EditText>(R.id.indicList).text.toString()
            addlists(hash, text)
            getListe(hash)
            //Toast.makeText(this, user.listActivite.toString(), Toast.LENGTH_LONG).show()
        }

    }

    private val mainActivityScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    override fun onDestroy() {
        super.onDestroy()
        mainActivityScope.cancel()
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
        private val dataSet: listList,
        private val hash : String
    ) : RecyclerView.Adapter<ChoixListActivity.ListAdapter.ItemViewHolder>() {


        override fun getItemCount(): Int = dataSet.lists.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)

            return ItemViewHolder(itemView = itemView)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(list = dataSet.lists[position])
            holder.itemView.setOnClickListener {
                val context = holder.itemView.context
                val intent = Intent(context, ShowListActivity::class.java)
                intent.putExtra("idListe", dataSet.lists[position].id)
                intent.putExtra("hash", hash)
                context.startActivity(intent)
            }
        }


        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val textListe = itemView.findViewById<TextView>(R.id.textList)

            fun bind(list: listApi) {
                textListe.text = list.label
            }


        }
    }
    private fun getItem1113(hash : String){ //debug pour resoudre le pb d'affichage des items
        Log.v("myActivity","appel getItem")
        mainActivityScope.launch {
            val lists = DataProvider.getItems1113(hash)
            Log.v("myActivity",lists.toString())

        }
    }
    private fun getListe(hash: String){
        Log.v("myActivity","appel getlist")
        mainActivityScope.launch {
            val list = findViewById<RecyclerView>(R.id.list)
            val lists = myDataProvider.getLists(hash)
            Log.v("myActivity", "la liste : $lists")
            list.adapter = ListAdapter(dataSet = lists, hash=hash)
            list.layoutManager = LinearLayoutManager(applicationContext, VERTICAL, false)
            Log.v("myActivity","RecyclerView créé")

        }
    }
    private fun addlists(hash : String,text: String) {
        Log.v("myActivity","appel addlists")
        mainActivityScope.launch {
            if (text.toString()!=null) {
                val lists = DataProvider.addList(hash,text)
                Log.v("myActivity","ajoutList")
                }
            else Log.v("myActivity","text vide")

        }
    }


    fun checkInternet(context : Context) : Boolean {
        val connectivityManager = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
        Log.d("myActivity", "Testwifi")
        return if(activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) { // Si on est connecté en wifi -> on a internet
            Log.d("myActivity", "wifi")
            true
        } else activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) // Sinon on retourne si on est connecté en 4g

    }
}

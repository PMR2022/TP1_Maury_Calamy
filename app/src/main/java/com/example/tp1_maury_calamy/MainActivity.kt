package com.example.tp1_maury_calamy


import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.*
import android.widget.*
import com.example.tp1_maury_calamy.DataClass.ListeToDo
import retrofit2.Call
import com.example.tp1_maury_calamy.DataClass.ProfilListeToDo
import com.google.gson.Gson
import retrofit2.Callback
import retrofit2.Response
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var menuList: Menu
    private lateinit var preferences : SharedPreferences
    private lateinit var indicPseudo : EditText

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("myActivity", "création")
        indicPseudo = findViewById(R.id.indicPseudo) //Variable contenant le pseudo renseigné
        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        val btnOk : Button = findViewById(R.id.btnOk)
        val showBtn = checkInternet(this)
        if(!showBtn) {
            btnOk.isEnabled = false
        }
        btnOk.setOnClickListener {
            Log.d("myActivity", "appuiBtn")

            // On commence par modifier les préférences
            val editeur = preferences.edit()
            editeur.putString("Pseudo", indicPseudo.text.toString())
            editeur.commit()

            // on met à jour le fichier json
            var gson = Gson()
            var jsonString = gson.toJson(ProfilListeToDo(indicPseudo.text.toString(), ArrayList()))

            // Et après on change d'activité
            val choixListActivity = Intent(this,ChoixListActivity::class.java)
            choixListActivity.putExtra("pseudo", indicPseudo.text.toString())
            startActivity(choixListActivity)
            Log.d("myActivity", "chgt Activité")

        }

        val apiInterface = ApiInterface.create().getList()
        Log.v("myActivity", "lancement API" )
        mainActivityScope.launch {
            val listItem = apiInterface
            Log.v("myActivity", listItem.toString())
        }

    }
    private val mainActivityScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    override fun onDestroy() {
        super.onDestroy()
        mainActivityScope.cancel()
    }

    override fun onStart() {
        var pseudo = preferences.getString("Pseudo","")
        indicPseudo.setText(pseudo)
        super.onStart()


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







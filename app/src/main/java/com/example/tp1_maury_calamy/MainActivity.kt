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
import kotlinx.coroutines.*
import com.example.tp1_maury_calamy.db.DataProviderSql

class MainActivity : AppCompatActivity() {

    private lateinit var menuList: Menu
    private lateinit var preferences : SharedPreferences
    private lateinit var indicPseudo : EditText
    private lateinit var indicPass : EditText
    private val dataProvider : DataProvider2 by lazy {DataProvider2(this.application)}

    override fun onCreate(savedInstanceState: Bundle?) {


        //init bdd si vide :

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("myActivity", "création")
        indicPseudo = findViewById(R.id.indicPseudo) //Variable contenant le pseudo renseigné
        indicPass = findViewById(R.id.indicPass) //Variable contenant le mdp renseigné
        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        val btnOk : Button = findViewById(R.id.btnOk)

        btnOk.setOnClickListener {
            val internetOn = checkInternet(this)

            if(internetOn) {    // dans le cas où internet est up, on utilise l'api


                // on met à jour le fichier json

                // Et après on change d'activité
                val choixListActivity = Intent(this, ChoixListActivity::class.java)
                choixListActivity.putExtra("hash", indicPseudo.text.toString())
                startActivity(choixListActivity)

            }

            else{ //dans le cas où internet est down, on utilise la database


                //On récupère les données de la database et on cahnge d'activité
                displayUserLists(indicPseudo.text.toString(),indicPass.text.toString())

            }

        }

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
    private val mainActivityScope = CoroutineScope(
        SupervisorJob() + Dispatchers.Main
    )

    override fun onDestroy() {
        super.onDestroy()
        mainActivityScope.cancel()
    }

    private fun displayUserLists (pseudo : String, mdp : String){
        mainActivityScope.launch {
           var userHash : String = ""
            runCatching {
               userHash =  dataProvider.getUserHash(pseudo,mdp)
            }.fold(
                onSuccess = { items -> //Si on trouve l'user
                        val choixListActivity = Intent(this@MainActivity, ChoixListActivity::class.java)
                        choixListActivity.putExtra("hash", "1ae544e6fdef4e71d2a2c3797e8cad13")
                        startActivity(choixListActivity)

                },
                onFailure = { //Sinon
                    Toast.makeText(this@MainActivity,"Le pseudo ou le mot de passe est incorect",Toast.LENGTH_LONG).show()
                }
            )

        }
    }

}







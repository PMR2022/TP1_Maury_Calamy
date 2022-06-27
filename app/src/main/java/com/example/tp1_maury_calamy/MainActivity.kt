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
import com.example.tp1_maury_calamy.db.DataProviderSql

class MainActivity : AppCompatActivity() {

    private lateinit var menuList: Menu
    private lateinit var preferences : SharedPreferences
    private lateinit var indicPseudo : EditText
    private lateinit var indicPass : EditText
    private val dataProviderSql : DataProviderSql by lazy {DataProviderSql(this.application)}

    override fun onCreate(savedInstanceState: Bundle?) {

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

                // On commence par modifier les préférences
                val editeur = preferences.edit()
                editeur.putString("Pseudo", indicPseudo.text.toString())
                editeur.commit()

                // on met à jour le fichier json
                var gson = Gson()
                var jsonString =
                    gson.toJson(ProfilListeToDo(indicPseudo.text.toString(), ArrayList()))

                // Et après on change d'activité
                val choixListActivity = Intent(this, ChoixListActivity::class.java)
                choixListActivity.putExtra("pseudo", indicPseudo.text.toString())
                startActivity(choixListActivity)
            }

            else{ //dans le cas où internet est down, on utilise la database

                // On commence par modifier les préférences
                val editeur = preferences.edit()
                editeur.putString("Pseudo", indicPseudo.text.toString())
                editeur.commit()

                //On récupère les données de la database et on cahnge d'activité
                displayUserLists(indicPseudo.text.toString(),indicPass.text.toString())

            }

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

    private fun displayUserLists (pseudo : String, mdp : String){
        mainActivityScope.launch {
            var res  = -1
            runCatching {
               res =  dataProviderSql.getUserId(pseudo,mdp)  //on va chercher l'id de l'user correspondant à ce pseudo et ce mot de passe
            }.fold(
                onSuccess = { items ->
                    if(res>-1) { // si on trouve l'user dans la database, on lance l'activité choix liste en lui passant son id
                        val choixListActivity = Intent(this@MainActivity, ChoixListActivity::class.java)
                        choixListActivity.putExtra("id", res)
                        startActivity(choixListActivity)
                    }

                    else{ // si on ne trouve pas l'user dans la database, on affiche un message d'erreur
                        Toast.makeText(this@MainActivity,"Le pseudo ou le mot de passe est incorect",Toast.LENGTH_LONG).show()
                    }
                },
                onFailure = {
                    Log.e("MainActivity", "Fails -> $it")
                }
            )

        }
    }


}







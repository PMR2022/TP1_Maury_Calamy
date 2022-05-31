package com.example.tp1_maury_calamy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ChoixListActivity : AppCompatActivity() {
    private lateinit var user : User
    private lateinit var listeName : EditText
    private lateinit var newListe : Liste
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.choix_list_activity)

        listeName = findViewById(R.id.nouvelleList)
        val pseudo = intent.getStringExtra("pseudo").toString()
        //Toast.makeText(this, pseudo, Toast.LENGTH_LONG).show() //test pseudo
        // TODO : récupérer le user à partir du pseudo et l'afficher, pour l'instant je me contente d'en créer un vierge
        user.name = pseudo
        user.listActivite= ArrayList()   // à récup avec GSON

        val btnOk : Button = findViewById(R.id.btnOkNewList)
        btnOk.setOnClickListener {
            newListe.name = listeName.text.toString()
            newListe.listItem = ArrayList()
            user.listActivite.add(newListe)
            Toast.makeText(this, user.listActivite.toString(), Toast.LENGTH_LONG).show()
        }

        // TODO : écouter les click sur les différentes listes et ouvrir ShowListActivity en lui passant le nom de la liste en argument

        /*          bout de code utile pour ça

          val showListActivity = Intent(this,ShowListActivity::class.java)
          choixListActivity.putExtra("liste", LeNomDeLaListeCliquée)
          startActivity(showListActivity)

        */


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
}

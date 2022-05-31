package com.example.tp1_maury_calamy

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var menuList: Menu
    private lateinit var preferences : SharedPreferences
    private lateinit var indicPseudo : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        indicPseudo = findViewById(R.id.indicPseudo) //Variable contenant le pseudo renseigné
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val btnOk : Button = findViewById(R.id.btnOk)
        btnOk.setOnClickListener {

            // On commence par modifier les préférences
            val editeur = preferences.edit()
            editeur.putString("Pseudo", indicPseudo.text.toString())
            editeur.commit()

            // Et après on change d'activité
            val choixListActivity = Intent(this,ChoixListActivity::class.java)
            choixListActivity.putExtra("pseudo", indicPseudo.text.toString())
            startActivity(choixListActivity)
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
}


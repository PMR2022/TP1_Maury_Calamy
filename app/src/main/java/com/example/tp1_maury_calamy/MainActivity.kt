package com.example.tp1_maury_calamy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val indicPseudo : EditText = findViewById(R.id.indicPseudo) //Variable contenant le pseudo renseigné
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val btnOk : Button = findViewById(R.id.btnOk)
        btnOk.setOnClickListener {

            // On commence par modifier les préférences
            val editeur = preferences.edit()
            editeur.putString("Pseudo", indicPseudo.text.toString())
            editeur.commit()

            // Et après on change d'activité
            val choixListActivity = Intent(this,ChoixListActivity::class.java)
            startActivity(choixListActivity)
        }
    }
}

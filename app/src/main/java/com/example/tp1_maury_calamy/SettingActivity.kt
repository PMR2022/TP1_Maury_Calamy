package com.example.tp1_maury_calamy

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SettingActivity : AppCompatActivity() {
    private lateinit var preferences : SharedPreferences
    private lateinit var indicPseudo : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        indicPseudo = findViewById(R.id.pseudoPerf)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)
    }


    override fun onStart() {
        var pseudo = preferences.getString("Pseudo","")
        indicPseudo.setText(pseudo)
        super.onStart()


    }
}

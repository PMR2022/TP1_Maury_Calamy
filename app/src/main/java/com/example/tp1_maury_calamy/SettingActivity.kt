package com.example.tp1_maury_calamy

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity


class SettingActivity : AppCompatActivity() {
    private lateinit var preferences : SharedPreferences
    private lateinit var indicPseudo : EditText
    private lateinit var indicUrl : EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        indicPseudo = findViewById(R.id.pseudoPerf)
        indicUrl = findViewById(R.id.indicUrl)
        preferences = PreferenceManager.getDefaultSharedPreferences(this)

        indicUrl.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {
                val editeur = preferences.edit()
                editeur.putString("url", indicUrl.text.toString())
                editeur.commit()
            }

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
            }
        })

    }


    override fun onStart() {
        var pseudo = preferences.getString("Pseudo","")
        var url = preferences.getString("url","")
        if (url == ""){
            val editeur = preferences.edit()
            editeur.putString("url", "http://tomnab.fr/todo-api/")
            editeur.commit()
            url == "http://tomnab.fr/todo-api/"
        }

        indicPseudo.setText(pseudo)
        indicUrl.setText(url)
        super.onStart()
    }



}

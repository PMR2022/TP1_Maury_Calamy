package com.example.tp1_maury_calamy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Button
import android.widget.EditText
import com.google.gson.Gson
import java.io.File
import java.io.FileWriter
import java.io.PrintWriter
import java.nio.charset.Charset

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val indicPseudo : EditText = findViewById(R.id.indicPseudo)
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        val btnOk : Button = findViewById(R.id.btnOk)
        btnOk.setOnClickListener {

        }
    }
    var listePseudo = emptyArray<Pseudo>();
    var jsonString: String? = null;
    var gson= Gson();
    fun updateJson(){
        jsonString = gson.toJson(listePseudo);
    }


}
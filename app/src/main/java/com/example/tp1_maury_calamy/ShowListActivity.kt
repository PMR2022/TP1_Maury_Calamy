package com.example.tp1_maury_calamy

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class ShowListActivity: AppCompatActivity()  {
    private lateinit var liste : Liste
    private lateinit var itemDescription : EditText
    private lateinit var newItem : Item
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)

        itemDescription = findViewById(R.id.nouvelItem)
        val listeName = intent.getStringExtra("liste").toString()
        // TODO : afficher les items de la liste "liste", pour l'instant je considère qu'elle est vide
        liste.name = listeName
        liste.listItem = ArrayList()  //pareil à changer avec gson
        val btnOk : Button = findViewById(R.id.btnOkNewItem)
        btnOk.setOnClickListener {
            newItem.description = itemDescription.text.toString()
            liste.listItem.add(newItem)
        }


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

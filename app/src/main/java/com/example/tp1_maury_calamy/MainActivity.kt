package com.example.tp1_maury_calamy

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView

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
        btnOk.setOnClickListener {
            Log.d("myActivity", "appuiBtn")
            // On commence par modifier les préférences
            val editeur = preferences.edit()
            editeur.putString("Pseudo", indicPseudo.text.toString())
            editeur.commit()
            Log.d("myActivity", "chgt Activité")
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

    class ItemAdapter(
        private val dataSet: List<Item>
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

        override fun getItemCount(): Int = dataSet.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

            return ItemViewHolder(itemView = itemView)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(item = dataSet[position])
        }
        /*fun provideDataSet(): List<Item> {
            val result = mutableListOf<Item>()
            repeat(1_000) { intex ->
                val item = Item(
                    imageRes = R.mipmap.ic_launcher,
                    title = "Titre $intex",
                    subTitle = "Sous-Titre $intex",
                )

                result.add(item)
            }
            return result
        }*/

        class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val textItem = itemView.findViewById<TextView>(R.id.textItem)
            private val checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)

            fun bind(item: Item) {
                textItem.text = item.description
                checkBox.setChecked() =item.fait
            }

        }
}



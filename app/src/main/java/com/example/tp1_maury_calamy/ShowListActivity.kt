package com.example.tp1_maury_calamy

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.tp1_maury_calamy.DataClass.ItemApi
import com.example.tp1_maury_calamy.DataClass.listItem
import com.google.gson.Gson

class ShowListActivity: AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)



        val btnOk : Button = findViewById(R.id.btnOkNewItem)
        btnOk.setOnClickListener {

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



    class ItemAdapter(
        private val dataSet: listItem
    ) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {



        override fun getItemCount(): Int = dataSet.listItemApi.size

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val itemView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)

            return ItemViewHolder(itemView = itemView)
        }

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
            holder.bind(item = dataSet.listItemApi[position])
        }


        inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val textItem = itemView.findViewById<TextView>(R.id.textItem)
            private val checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)

            fun bind(item: ItemApi) {
                textItem.text = item.label
                checkBox.isChecked = item.checked
            }

        }
    }



}

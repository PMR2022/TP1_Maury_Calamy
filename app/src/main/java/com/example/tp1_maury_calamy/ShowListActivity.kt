package com.example.tp1_maury_calamy

import androidx.appcompat.app.AppCompatActivity

class ShowListActivity: AppCompatActivity()  {
   /* private lateinit var listeToDo : ListeToDo
    private lateinit var itemDescription : EditText
    private lateinit var newItem : ItemToDo
    private lateinit var listeData : AllData // Cette variable contient la liste de tous les profils. On s'en sert pour serializer/désérializer

    override fun onCreate(savedInstanceState: Bundle?) {
        listeData = deserialize() //lecture du fichier de données

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_list)

        //Toast.makeText(this, "activité show liste", Toast.LENGTH_LONG).show()
        itemDescription = findViewById(R.id.nouvelItem)
        val listeName = intent.getStringExtra("liste").toString()
        val pseudo = intent.getStringExtra("liste").toString()
        // TODO : afficher les items de la liste "liste", pour l'instant je considère qu'elle est vide

        listeToDo = getListeToDo(pseudo,listeName)

        var adapter = ItemAdapter(dataSet = listeToDo.listItem)
        val listRecycler = findViewById<RecyclerView>(R.id.list) //création du recyclerView


        listRecycler.adapter = adapter
        listRecycler.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val btnOk : Button = findViewById(R.id.btnOkNewItem)
        btnOk.setOnClickListener {
            newItem= ItemToDo(itemDescription.text.toString())
            listeToDo.listItem.add(newItem)
            serialize()
            listRecycler.adapter!!.notifyDataSetChanged()
        }


    }

    private fun getListeToDo(pseudo: String, listeName: String): ListeToDo {
// Cette méthode permet d'estraire une ListeToDo des données grace a son nom

       // on commence par trouver le profil concerné
        var indice = 0
        var cpt = 0
        for (profil in listeData.listeProfils){
            if (profil.name == pseudo){
                indice = cpt
            }
            cpt++
        }

        var res = ListeToDo(listeName, ArrayList())
        var found = false
        for (listeToDo in listeData.listeProfils.get(indice).listActivite){
            if(listeToDo.titreListeToDo == listeName){
                res = listeToDo
                found = true
            }
        }

        if (found == false) {
            listeData.listeProfils.get(indice).listActivite.add(res)
        }
        return(res)
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
        private val dataSet: ArrayList<ItemToDo>
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


        inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val textItem = itemView.findViewById<TextView>(R.id.textItem)
            private val checkBox = itemView.findViewById<CheckBox>(R.id.checkBox)

            fun bind(item: ItemToDo) {
                textItem.text = item.description
                checkBox.isChecked = item.fait
            }

        }
    }

    fun ecrireFichier(txt: String){
        File(this.filesDir, "save.txt").outputStream().use {
            it.write(txt.toByteArray())
        }
    }

    fun lireFichier(): String {
        var recuperation : String
        try {
            recuperation = File(this.filesDir, "save.txt").bufferedReader().readText();
        }
        catch(e: Exception){ // pour prévenir d'un crash quand le fichier de sauvegarde n'existe pas
            recuperation = "empty"
        }
        return(recuperation)
    }

    fun serialize(){
        val gson = Gson()
        var jsonString = gson.toJson(listeData)
        ecrireFichier(jsonString)
    }

    fun deserialize(): AllData {
        val data = lireFichier()
        val gson = Gson()
        var testModel : AllData
        if (data == "empty"){
            testModel = AllData(ArrayList())

        }
        else {
            testModel = gson.fromJson(data, AllData::class.java)
        }
        return (testModel)
    }*/
}

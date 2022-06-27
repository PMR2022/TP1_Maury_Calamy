package com.example.tp1_maury_calamy

import android.app.Application
import android.util.Log
import androidx.room.Room
import com.example.tp1_maury_calamy.DataClass.ItemApi
import com.example.tp1_maury_calamy.DataClass.listApi
import com.example.tp1_maury_calamy.DataClass.listItem
import com.example.tp1_maury_calamy.DataClass.listList
import com.example.tp1_maury_calamy.db.DataBase
import com.example.tp1_maury_calamy.db.dataTypes.Lists
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception

class DataProvider2(app : Application) {
    private val token = "1ae544e6fdef4e71d2a2c3797e8cad13"
    // partie API
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://tomnab.fr/todo-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create<ApiInterface>()

    // partie SQLite
    private val database = Room.databaseBuilder(
        app,
        DataBase::class.java, "sequence2"
    ).build()

    private val SqlDao = database.sqlDao()

    suspend fun getLists(userHash : String) : listList {
        Log.v("debug",SqlDao.printList().toString())
        var  userId = 0
        return try {
            Log.v("debug","retre dans fct")
            val lists = service.getList()
            // On enregistre les données dans la base de données
            val listForDb = TypeTransform(lists, userId)
            Log.v("debug","ok avant save db")
            SqlDao.saveOrUpdateLists(listForDb!!)
            lists
        } catch(e : Exception){
            Log.v("debug","exception donc sql")
            val lists = TypeTransform(SqlDao.getLists(userId))
            Log.v("debug","ok sql")
            lists
        }
    }
    suspend fun getUserHash(name : String, pass : String): String  {
        return SqlDao.getUserHash(name, pass)
    }
    suspend fun getItems1113() : listItem = service.getItems1113()
    suspend fun getItems(idList:Int) : listItem = service.getItems(idList)
    suspend fun addList(listName:String) : listApi = service.createList(listName)
    suspend fun addItem(idList : Int,itemName:String) : ItemApi = service.createItem(idList,itemName)
    suspend fun check(idList:Int,idItem:Int,checked:Boolean) = service.check(idList,idItem,checked)
    suspend fun getUserIdByHash(hash : String) : Int = SqlDao.getUserIdByHash(hash).toInt()
    fun TypeTransform(lists : listList, userId : Int) : List<Lists>{
        // Cette fonction sert à transformer un objet de type "listList" en List<Lists>  utilisable par la database SQLite
        var res : List<Lists> = listOf()
        for (list in lists.lists){
            var ListsTmp = Lists(id = list.id, idUser = userId, label = list.label)
            res += ListsTmp
        }
        return res

    }

    fun TypeTransform(lists : List<Lists>) : listList {
        // Cette fonction sert à transformer un objet de type "listList" en List<Lists>  utilisable par la database SQLite
        val init : List<listApi> = listOf()
        var res  : listList = listList(init)
        for (list in lists){
            var ListsTmp : listApi = listApi(id = list.id, label = list.label)
            res.lists += ListsTmp
        }
        return res

    }

}
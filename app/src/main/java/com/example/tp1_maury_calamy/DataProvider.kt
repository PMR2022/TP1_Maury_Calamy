package com.example.tp1_maury_calamy



import android.app.Application
import androidx.room.Room
import com.example.tp1_maury_calamy.DataClass.*
import com.example.tp1_maury_calamy.db.DataBase
import com.example.tp1_maury_calamy.db.dataTypes.Lists
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import java.lang.Exception

object DataProvider{
    private val token = "1ae544e6fdef4e71d2a2c3797e8cad13"
    // partie API
    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://tomnab.fr/todo-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create<ApiInterface>()





    suspend fun getLists(hash: String): listList = service.getList(hash)
    suspend fun getItems1113(hash: String) :  listItem = service.getItems1113(hash)
    suspend fun getItems(hash: String, idList:Int) : listItem = service.getItems(hash, idList)
    suspend fun addList(hash: String ,listName:String) : listApi = service.createList(hash, listName)
    suspend fun addItem(hash: String, idList : Int,itemName:String) : ItemApi = service.createItem(hash,idList,itemName)
    suspend fun check(hash: String, idList:Int,idItem:Int,checked:Boolean) = service.check(hash,idList,idItem,checked)
    suspend fun auth(login: String, password: String): authentification = service.auth(login, password)



}
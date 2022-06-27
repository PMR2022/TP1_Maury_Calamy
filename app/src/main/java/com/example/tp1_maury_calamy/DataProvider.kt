package com.example.tp1_maury_calamy



import com.example.tp1_maury_calamy.DataClass.ItemApi
import com.example.tp1_maury_calamy.DataClass.listApi
import com.example.tp1_maury_calamy.DataClass.listList
import com.example.tp1_maury_calamy.DataClass.listItem
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object DataProvider {
    private val token = "1ae544e6fdef4e71d2a2c3797e8cad13"

    val retrofit : Retrofit = Retrofit.Builder()
        .baseUrl("http://tomnab.fr/todo-api/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create<ApiInterface>()

    suspend fun getLists() :  listList = service.getList()
    suspend fun getItems1113() :  listItem = service.getItems1113()
    suspend fun getItems(idList:Int) : listItem = service.getItems(idList)
    suspend fun addList(listName:String) : listApi = service.createList(listName)
    suspend fun addItem(idList : Int,itemName:String) : ItemApi = service.createItem(idList,itemName)
    suspend fun check(idList:Int,idItem:Int,checked:Boolean) = service.check(idList,idItem,checked)

}
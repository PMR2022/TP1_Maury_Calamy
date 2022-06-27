package com.example.tp1_maury_calamy.db

import android.app.Application
import androidx.room.Room
import com.example.tp1_maury_calamy.db.dataTypes.Item
import com.example.tp1_maury_calamy.db.dataTypes.Lists

class DataProviderSql(app : Application) {
    private val database = Room.databaseBuilder(
        app,
        DataBase::class.java, "sequence2"
    ).build()

    private val SqlDao = database.sqlDao()

    suspend fun getUserId(name : String, pass : String): Int  {
        return SqlDao.getUserId(name, pass)
    }

    suspend fun getUserHash(name : String, pass : String): String  {
        return SqlDao.getUserHash(name, pass)
    }

    suspend fun getLists(idUser : Int) : List<Lists>{
        return SqlDao.getLists(idUser)
    }

    suspend fun getItems(idList : Int) : List<Item>{
        return SqlDao.getItems(idList)
    }

    suspend fun saveOrUpdateLists (list : List<Lists>){
        SqlDao.saveOrUpdateLists(list)
    }

}
package com.example.tp1_maury_calamy.db.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tp1_maury_calamy.db.dataTypes.Item
import com.example.tp1_maury_calamy.db.dataTypes.Lists

@Dao
interface SqlDao {

    @Query("SELECT * FROM ITEM WHERE idList == :idList")
    suspend fun getItems(idList : Int) : List<Item>

    @Query("SELECT id FROM USER WHERE pseudo LIKE :name AND pass LIKE :pass ")
    suspend fun getUserId(name : String, pass : String) : Int

    @Query("SELECT * FROM LISTS WHERE idUser == :idUser")
    suspend fun getLists(idUser : Int ) : List<Lists>
}
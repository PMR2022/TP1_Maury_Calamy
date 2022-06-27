package com.example.tp1_maury_calamy.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tp1_maury_calamy.db.dataTypes.Item
import com.example.tp1_maury_calamy.db.dataTypes.Lists
import com.example.tp1_maury_calamy.db.dataTypes.User

@Dao
interface SqlDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdateLists(Lists : List<Lists>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveOrUpdateItems(Item : List<Item>)

    @Query("SELECT * FROM ITEM WHERE idList == :idList")
    suspend fun getItems(idList : Int) : List<Item>

    @Query("SELECT id FROM USER WHERE pseudo LIKE :name AND pass LIKE :pass ")
    suspend fun getUserId(name : String, pass : String) : Int

    @Query("SELECT hash FROM USER WHERE pseudo LIKE :name AND pass LIKE :pass ")
    suspend fun getUserHash(name : String, pass : String) : String

    @Query("SELECT id FROM USER WHERE hash LIKE :hash")
    suspend fun getUserIdByHash(hash : String) : Int

    @Query("SELECT * FROM USER ")
    suspend fun printUser() : List<User>

    @Query("SELECT * FROM LISTS ")
    suspend fun printList() : List<Lists>




    @Query("SELECT * FROM LISTS WHERE idUser == :idUser")
    suspend fun getLists(idUser : Int ) : List<Lists>
}
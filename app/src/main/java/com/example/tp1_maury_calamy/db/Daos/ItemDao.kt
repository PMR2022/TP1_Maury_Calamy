package com.example.tp1_maury_calamy.db.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tp1_maury_calamy.db.dataTypes.Item
@Dao
interface ItemDao {

    @Query("SELECT * FROM ITEM WHERE idList == :idList")
    suspend fun getItems(idList : Int) : List<Item>
}
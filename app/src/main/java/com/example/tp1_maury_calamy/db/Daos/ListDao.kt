package com.example.tp1_maury_calamy.db.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tp1_maury_calamy.db.dataTypes.Lists

@Dao
interface ListDao {

    @Query("SELECT * FROM LISTS WHERE idUser == :idUser")
    suspend fun getLists(idUser : Int ) : List<Lists>
}
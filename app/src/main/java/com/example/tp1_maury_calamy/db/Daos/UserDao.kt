package com.example.tp1_maury_calamy.db.Daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tp1_maury_calamy.db.dataTypes.User

@Dao
interface UserDao {

    @Query("SELECT * FROM USER WHERE pseudo LIKE :name AND pass LIKE :pass ")
    suspend fun getUser(name : String, pass : String) : User
}
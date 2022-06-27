package com.example.tp1_maury_calamy.db.DataBases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp1_maury_calamy.db.dataTypes.User
import com.example.tp1_maury_calamy.db.Daos.UserDao

@Database(entities = [User::class],version = 1)
abstract class UsersDataBases : RoomDatabase(){

    abstract fun userDao() : UserDao
}
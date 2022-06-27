package com.example.tp1_maury_calamy.db.DataBases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp1_maury_calamy.db.dataTypes.Item
import com.example.tp1_maury_calamy.db.dataTypes.Lists
import com.example.tp1_maury_calamy.db.dataTypes.User
import com.example.tp1_maury_calamy.db.Daos.SqlDao


@Database(entities = [Item::class, Lists::class, User::class],version = 1)
abstract class DataBase : RoomDatabase(){

    abstract fun itemDao() : SqlDao
}
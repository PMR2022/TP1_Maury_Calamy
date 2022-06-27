package com.example.tp1_maury_calamy.db.DataBases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp1_maury_calamy.db.dataTypes.Lists
import com.example.tp1_maury_calamy.db.Daos.ListDao

@Database(entities = [Lists::class],version = 1)
abstract class ListsDataBase : RoomDatabase(){

    abstract fun listsDao() : ListDao
}
package com.example.tp1_maury_calamy.db.DataBases

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tp1_maury_calamy.db.dataTypes.Item
import com.example.tp1_maury_calamy.db.Daos.ItemDao


@Database(entities = [Item::class],version = 1)
abstract class ItemsDataBase : RoomDatabase(){

    abstract fun itemDao() : ItemDao
}
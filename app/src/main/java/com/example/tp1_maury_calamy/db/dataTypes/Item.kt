package com.example.tp1_maury_calamy.db.dataTypes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Item (
    @PrimaryKey
    val id : Int,
    val idList : Int,
    val label : String,
    val checked : Boolean,
    val url : String
        )
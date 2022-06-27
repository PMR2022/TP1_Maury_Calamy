package com.example.tp1_maury_calamy.db.dataTypes

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class User (
    @PrimaryKey
    val id : Int,
    val pseudo : String,
    val pass : String,
    val hash : String
        )
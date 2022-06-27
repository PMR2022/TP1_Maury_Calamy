package com.example.tp1_maury_calamy.db.dataTypes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Lists (
    @PrimaryKey
    val id : Int,
    val idUser : Int,
    val label : String
        )
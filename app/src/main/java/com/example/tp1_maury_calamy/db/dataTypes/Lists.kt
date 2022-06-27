package com.example.tp1_maury_calamy.db.dataTypes
import androidx.room.Entity


@Entity
data class Lists (
    @androidx.room.PrimaryKey
    val id : Int,
    val idUser : Int,
    val label : String
        )
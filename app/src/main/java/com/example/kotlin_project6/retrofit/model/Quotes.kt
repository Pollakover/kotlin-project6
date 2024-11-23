package com.example.kotlin_project6.retrofit.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")

data class Quotes (
    @PrimaryKey val id: Int,
    val quote: String,
    val author: String
)
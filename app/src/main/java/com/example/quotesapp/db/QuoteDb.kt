package com.example.quotesapp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes_db")
data class QuoteDb(

    @PrimaryKey(autoGenerate = true) var id: Int?,

    @ColumnInfo var quote: String,

    @ColumnInfo var author: String

)
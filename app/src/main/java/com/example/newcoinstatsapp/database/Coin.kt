package com.example.newcoinstatsapp.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "_coin_table")
class Coin {
    @PrimaryKey
    @ColumnInfo(name = "_id")
    var id: String = ""

    @ColumnInfo(name = "_identifier")
    var identifier: String? = null

    @ColumnInfo(name = "_isFavorite")
    var isFavorite: Boolean = false

    @ColumnInfo(name = "_icon")
    var icon: String? = null

    @ColumnInfo(name = "_name")
    var name: String? = null

    @ColumnInfo(name = "_price")
    var price: String? = null
}
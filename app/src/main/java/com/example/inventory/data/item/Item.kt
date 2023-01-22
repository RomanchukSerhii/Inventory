package com.example.inventory.data.item

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class Item (
    @PrimaryKey @ColumnInfo(name = "id")val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: Int,
    @ColumnInfo(name = "quantity") val quantity: Int
)
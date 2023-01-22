package com.example.inventory.data.item

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getItems(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): LiveData<Item>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addItem(item: Item)

    @Query("DELETE FROM items WHERE id = :id")
    fun remove(id: Int)
}
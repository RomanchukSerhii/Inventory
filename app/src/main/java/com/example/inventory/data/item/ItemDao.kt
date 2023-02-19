package com.example.inventory.data.item

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ItemDao {
    @Query("SELECT * FROM items")
    fun getItems(): LiveData<List<Item>>

    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): LiveData<Item>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addItem(item: Item)

    @Query("DELETE FROM items WHERE id = :id")
    suspend fun remove(id: Int)

    @Update
    suspend fun updateItem(item: Item)

    @Query("UPDATE items SET quantity = :quantity WHERE id = :id")
    suspend fun changeQuantity(id: Int, quantity: Int)
}
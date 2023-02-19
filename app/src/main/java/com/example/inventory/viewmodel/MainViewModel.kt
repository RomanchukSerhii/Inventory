package com.example.inventory.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.AppDatabase
import com.example.inventory.data.item.Item
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val itemDao = AppDatabase.getDatabase(application).itemDao()
    val itemsListLiveData = itemDao.getItems()

    fun getItem(id: Int): LiveData<Item> {
        return itemDao.getItem(id)
    }

    fun addItem(name: String, price: Double, quantity: Int) {
        val item = Item(
            name = name,
            price = price,
            quantity = quantity
        )

        viewModelScope.launch {
            itemDao.addItem(item)
        }
    }

    fun replaceItem(item: Item) {
        viewModelScope.launch {
            itemDao.updateItem(item)
        }
    }

    fun changeQuantityItem(id: Int, quantity: Int) {
        viewModelScope.launch {
            itemDao.changeQuantity(id, quantity)
        }
    }

    fun removeItem(id: Int) {
        viewModelScope.launch {
            itemDao.remove(id)
        }
    }
}
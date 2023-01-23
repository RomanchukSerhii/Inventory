package com.example.inventory.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.inventory.data.AppDatabase
import com.example.inventory.data.item.Item
import kotlin.concurrent.thread

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

        thread {
            itemDao.addItem(item)
        }
    }

    fun replaceItem(id: Int, name: String, price: Double, quantity: Int) {
        thread {
            itemDao.replace(id, name, price, quantity)
        }
    }

    fun removeItem(item: Item) {
        thread {
            itemDao.remove(item.id)
        }
    }
}
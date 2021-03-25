package com.example.fluidrecyclerview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import kotlin.concurrent.thread

class ItemViewModel : ViewModel() {
    private val _itemLiveData = MutableLiveData<List<Int>>()
    val itemLiveData: LiveData<List<Int>> get() = _itemLiveData

    private val TAG = ItemViewModel::class.java.simpleName

    val coroutineScope = CoroutineScope(Job() + Dispatchers.IO)
    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception:${throwable.localizedMessage}", throwable)
    }

    fun generateItems() {
        _itemLiveData.postValue(itemList)
        coroutineScope.launch(exceptionHandler) {
            var count = 0
            while (true) {
                Thread.sleep(5000)
                itemList.add(++count)
                _itemLiveData.postValue(itemList.toList())
                Log.d(TAG, itemList.toString())
            }
        }
    }

    companion object {
        var itemList: MutableList<Int> = mutableListOf()
        val removedItems: MutableList<Int> = mutableListOf()
    }
}
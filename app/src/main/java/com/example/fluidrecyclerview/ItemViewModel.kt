package com.example.fluidrecyclerview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*

class ItemViewModel : ViewModel() {
    private val _itemLiveData = MutableLiveData<List<Int>>()
    val itemLiveData: LiveData<List<Int>> get() = _itemLiveData

    private val TAG = ItemViewModel::class.java.simpleName

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        Log.e(TAG, "Coroutine exception:${throwable.localizedMessage}", throwable)
    }

    init {
        generateItems()
    }

    fun generateItems() {
        _itemLiveData.postValue(itemList)
        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.Default) {
                while (true) {
                    itemList.add(++counter)
                    _itemLiveData.postValue(itemList.toList())
                    Log.d(TAG, itemList.toString())
                    Thread.sleep(5000)
                }
            }
        }
    }

    companion object {
        var itemList: MutableList<Int> = mutableListOf()
        val removedItems: MutableList<Int> = mutableListOf()
        var counter = 0
    }
}
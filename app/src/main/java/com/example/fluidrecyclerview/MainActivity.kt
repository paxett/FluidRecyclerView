package com.example.fluidrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    val itemViewModel : ItemViewModel = ItemViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.recycler)
        val rvAdapter = RvAdapter(this)
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = rvAdapter

        initObserver(rvAdapter)
        generateItems()
    }

    private fun generateItems() {
        itemViewModel.generateItems()
    }

    private fun initObserver(rvAdapter: RvAdapter) {
        itemViewModel.itemLiveData.observe(this, { itemList ->
            itemList?.run {
                rvAdapter.numbers = itemList
                rvAdapter.notifyDataSetChanged()
            }
        })
    }
}
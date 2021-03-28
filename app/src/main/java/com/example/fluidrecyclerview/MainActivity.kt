package com.example.fluidrecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)

        val rvAdapter = RvAdapter(itemViewModel)
        initObserver(rvAdapter, itemViewModel)

        this.findViewById<RecyclerView>(R.id.recycler).apply {
            adapter = rvAdapter
            layoutManager = GridLayoutManager(this.context, 2)
        }
    }

    private fun initObserver(rvAdapter: RvAdapter, itemViewModel: ItemViewModel) {
        itemViewModel.itemLiveData.observe(this, { itemList ->
            itemList?.run {
                rvAdapter.numbers = itemList
                rvAdapter.notifyDataSetChanged()
            }
        })
    }
}
package com.example.fluidrecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(context: Context) : RecyclerView.Adapter<ItemViewHolder>() {
    val numbers: List<Int> = listOf(1,2,3,4,5,6)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.item_viewholder, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(numbers.get(position))
    }

    override fun getItemCount(): Int {
        return numbers.size
    }
}
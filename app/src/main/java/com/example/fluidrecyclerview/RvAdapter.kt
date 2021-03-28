package com.example.fluidrecyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class RvAdapter(itemViewModel: ItemViewModel) : RecyclerView.Adapter<ItemViewHolder>() {
    var numbers: List<Int> = listOf()
    val itemViewModel = itemViewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.item_viewholder, parent, false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.number_tv.text = numbers.get(position).toString()
        holder.delete_btn.setOnClickListener {
            itemViewModel.deleteItem(numbers.get(position))
        }
    }

    override fun getItemCount(): Int = numbers.size
}
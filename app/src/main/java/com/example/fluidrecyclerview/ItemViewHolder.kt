package com.example.fluidrecyclerview

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val number_tv: TextView = itemView.findViewById(R.id.number_tv)
    fun bind(number: Int) {
        number_tv.text = number.toString()
    }

}
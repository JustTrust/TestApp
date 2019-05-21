package com.test.testapp.ui.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.test.testapp.network.models.Item

/**
 * Created by a.belichenko on 20.05.2019.
 * mail: a.belichenko@gmail.com
 */
class TitleAdapter(private val items: List<Item>, private val listener: (Item) -> Unit) :
    RecyclerView.Adapter<TitleAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: Item, listener: (Item) -> Unit) = with(itemView) {
            findViewById<TextView>(android.R.id.text1)?.text = item.title
            setOnClickListener { listener(item) }
        }
    }
}
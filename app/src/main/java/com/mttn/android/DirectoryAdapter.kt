package com.mttn.android

import android.content.Context
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class DirectoryAdapter(private val myDataset: ArrayList<Directory>, context: Context?) :
        RecyclerView.Adapter<DirectoryAdapter.MyViewHolder>() {

    val mContext = context

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryTitle: TextView = itemView.findViewById(R.id.category_title)

    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): DirectoryAdapter.MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.directory_item, parent, false) as CardView
        return MyViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.categoryTitle.text = myDataset[position].category
    }

    override fun getItemCount() = myDataset.size
}
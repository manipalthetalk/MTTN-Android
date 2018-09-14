package com.mttn.android

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



class AlertsAdapter(private val myDataset: ArrayList<Alerts>, context: Context?) :
        RecyclerView.Adapter<AlertsAdapter.MyViewHolder>() {

    val mContext = context

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var alertTitle: TextView = itemView.findViewById(R.id.alert_title)
        var alertContent: TextView = itemView.findViewById(R.id.alert_content)

        init {
            itemView.setOnClickListener { v: View? ->
                val position: Int = getAdapterPosition()
                mContext?.startActivity(openUrl(myDataset[position].Url))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): AlertsAdapter.MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.alert_item, parent, false) as CardView
        return MyViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.alertTitle.text = myDataset[position]?.Head
        holder.alertContent.text = myDataset[position]?.Body
    }

    override fun getItemCount() = myDataset.size

    fun openUrl(url: String?): Intent {
        val browserIntent = Intent(Intent.ACTION_VIEW,
                Uri.parse(url))
        return browserIntent
    }
}
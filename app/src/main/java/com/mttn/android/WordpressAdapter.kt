package com.mttn.android

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class WordpressAdapter(private val myDataset: ArrayList<Posts>, context: Context?) :
        RecyclerView.Adapter<WordpressAdapter.MyViewHolder>() {

    val mContext = context

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var postTitle: TextView = itemView.findViewById(R.id.post_title)
        var postImage: ImageView = itemView.findViewById(R.id.post_image)

        init {
            itemView.setOnClickListener { v: View? ->
                var position: Int = getAdapterPosition()
                mContext?.startActivity(launchNextScreen(mContext, myDataset[position]))
            }
        }

        fun updateWithUrl(url: String?) {
            Picasso.get().load(url).into(postImage);
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): WordpressAdapter.MyViewHolder {
        val cardView = LayoutInflater.from(parent.context)
                .inflate(R.layout.post_item, parent, false) as CardView

        return MyViewHolder(cardView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.postTitle.text = Html.fromHtml(myDataset[position].title)
        holder.updateWithUrl(myDataset[position].featured_image)
    }

    override fun getItemCount() = myDataset.size

    fun launchNextScreen(context: Context, posts: Posts): Intent {
        val intent = Intent(context, PostDetail::class.java)
        intent.putExtra("EXTRA_POST", posts)
        return intent
    }
}
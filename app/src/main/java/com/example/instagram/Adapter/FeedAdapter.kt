package com.example.instagram.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.Feed
import com.example.instagram.R

class FeedAdapter(val feedList: ArrayList<Feed>) : RecyclerView.Adapter<FeedAdapter.CustomViewHolder>(){

    class CustomViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImg = itemView.findViewById<ImageButton>(R.id.ib_profile)
        val profileName = itemView.findViewById<TextView>(R.id.tv_name)
        val feedImg = itemView.findViewById<ImageView>(R.id.iv_feed)
        val feedText = itemView.findViewById<TextView>(R.id.tv_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_feed_recycler, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.profileImg.setImageResource(feedList.get(position).profileImg)
        holder.profileName.text = feedList.get(position).profileName
        holder.feedImg.setImageResource(feedList.get(position).feedImg)
        holder.feedText.text = feedList.get(position).feedText
    }

    override fun getItemCount(): Int {
        return feedList.size
    }


}
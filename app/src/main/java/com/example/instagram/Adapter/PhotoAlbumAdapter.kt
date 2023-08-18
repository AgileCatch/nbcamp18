package com.example.instagram.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.PhotoCard
import com.example.instagram.R

class PhotoAlbumAdapter(var photocardList: ArrayList<PhotoCard>) : RecyclerView.Adapter<PhotoAlbumAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val photo = itemView.findViewById<ImageView>(R.id.iv_photo)
        val content = itemView.findViewById<TextView>(R.id.tv_content)
        val content_datetime = itemView.findViewById<TextView>(R.id.content_datetime)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photoalbum_recycler, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.title.text = photocardList[position].title
        holder.photo.setImageResource(photocardList[position].photo)
        holder.content.text = photocardList[position].content
        holder.content_datetime.text = photocardList[position].datetime
    }

    override fun getItemCount(): Int {
        return photocardList.size
    }
}

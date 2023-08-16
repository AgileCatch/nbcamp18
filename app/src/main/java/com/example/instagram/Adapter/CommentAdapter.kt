package com.example.instagram.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.Comment
import com.example.instagram.Data.Feed
import com.example.instagram.R

class CommentAdapter(val commentList:ArrayList<Comment>) : RecyclerView.Adapter<CommentAdapter.CustomViewHolder>(){

    class CustomViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.rv_name)
        val nicname = itemView.findViewById<TextView>(R.id.rv_nicname)
        val profileimg = itemView.findViewById<ImageView>(R.id.rv_profile)
        val datetime = itemView.findViewById<TextView>(R.id.datetime)
        val content =itemView.findViewById<TextView>(R.id.rv_content)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment_recycler, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text=commentList.get(position).name
        holder.nicname.text = commentList.get(position).nicname
        holder.profileimg.setImageResource(commentList.get(position).profileimg)
        holder.datetime.text = commentList.get(position).datetime
        holder.content.text = commentList.get(position).content
    }

    override fun getItemCount(): Int {
        return commentList.size
    }


}
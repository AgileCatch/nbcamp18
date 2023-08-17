package com.example.instagram.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.Comment
import com.example.instagram.Data.Userinfo
import com.example.instagram.R

class PhotoAdapter(var commentList:ArrayList<Comment>) : RecyclerView.Adapter<PhotoAdapter.CustomViewHolder>(){

    class CustomViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.lv_name)
        val nicname = itemView.findViewById<TextView>(R.id.lv_nicname)
        val profileimg = itemView.findViewById<ImageView>(R.id.lv_profile)
        val datetime = itemView.findViewById<TextView>(R.id.datetime)
        val content =itemView.findViewById<TextView>(R.id.lv_comment)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_comment_recycler, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text=commentList.get(position).name
        holder.nicname.text = commentList.get(position).nicname
        holder.profileimg.setImageResource(commentList.get(position).profileimg)
        holder.content.text = commentList.get(position).content

        val commentDate = commentList[position].datetime // 댓글의 날짜 데이터 가져오기
        holder.datetime.text = commentDate // 날짜를 표시
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

}
package com.example.instagram.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.Comment
import com.example.instagram.Data.PhotoCard
import com.example.instagram.R

class PhotoCardAdapter(var photocardList: ArrayList<PhotoCard>) : RecyclerView.Adapter<PhotoCardAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.findViewById<TextView>(R.id.tv_title)
        val photo = itemView.findViewById<ImageView>(R.id.iv_photo)
        val content = itemView.findViewById<TextView>(R.id.tv_content)
        val content_datetime = itemView.findViewById<TextView>(R.id.content_datetime)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_photocard_recycler, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.title.text = photocardList.get(position).title//제목
        holder.photo.setImageResource(photocardList.get(position).photo)
        holder.content.text = photocardList.get(position).content//내용

        val commentDate = photocardList[position].datetime // 사진의 날짜 데이터 가져오기
        holder.content_datetime.text = commentDate // 날짜를 표시
    }

    override fun getItemCount(): Int {
        return photocardList.size
    }

}
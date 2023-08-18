package com.example.instagram.Adapter

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.Userinfo
import com.example.instagram.Data.UserinfoSingleton
import com.example.instagram.DetailPage
import com.example.instagram.MyPage
import com.example.instagram.R


class FeedAdapter(val feedList: ArrayList<Userinfo>, var userList: List<Userinfo>) :
    RecyclerView.Adapter<FeedAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val itemCard = itemView.findViewById<CardView>(R.id.item_card)
        val profileImg = itemView.findViewById<ImageButton>(R.id.ib_profile)
        val profileName = itemView.findViewById<TextView>(R.id.tv_name)
        val feedImg = itemView.findViewById<ImageView>(R.id.iv_feed)
        val feedText = itemView.findViewById<TextView>(R.id.tv_text)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_feed_recycler, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val feedItem = feedList[position]

        holder.profileName.text = feedItem.id
        holder.feedText.text = feedItem.description

        if (feedItem.changedProfileImg == Uri.EMPTY) {
            holder.profileImg.setImageResource(feedItem.profileImg)
        } else {
            holder.profileImg.setImageURI(feedItem.changedProfileImg)
        }

        if (feedItem.changedMiniroomImg == Uri.EMPTY) {
            holder.feedImg.setImageResource(feedItem.miniroom)
        } else {
            holder.feedImg.setImageURI(feedItem.changedMiniroomImg)
        }

        holder.itemCard.setOnClickListener {
            if (position == 0) {
                val intent = Intent(it.context, MyPage::class.java)
                updateData(position)
                intent.putExtra("position", position)
                it.context.startActivity(intent)
                (it.context as Activity).overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                )
            } else {
                val intent = Intent(it.context, DetailPage::class.java)
                updateData(position)
                intent.putExtra("position", position)
                it.context.startActivity(intent)
                (it.context as Activity).overridePendingTransition(
                    R.anim.slide_in_right,
                    R.anim.slide_out_left
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return feedList.size
    }

    fun updateData(position: Int) {
        val userinfoList = UserinfoSingleton.getUserinfoList()
        val userinfo = userinfoList[position]

        UserinfoSingleton.todayIncrease(userinfo)
    }


}
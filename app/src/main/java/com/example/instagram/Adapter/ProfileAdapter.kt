package com.example.instagram.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.Userinfo
import com.example.instagram.DetailPage
import com.example.instagram.MyPage
import com.example.instagram.R

class ProfileAdapter(var profilelist: List<Userinfo>) :
    RecyclerView.Adapter<ProfileAdapter.CustomViewHolder>() {

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileimg = itemView.findViewById<ImageButton>(R.id.ib_profile)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile_recycler, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val userInfo = profilelist[position]
        holder.profileimg.setImageResource(userInfo.profileImg)
        holder.profileimg.setOnClickListener {
            if(position == profilelist.size -1){
                val intent = Intent(it.context, MyPage::class.java)
                intent.putExtra("position", position)
                it.context.startActivity(intent)
            }else{
                val intent = Intent(it.context, DetailPage::class.java)
                intent.putExtra("position", position)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return profilelist.size
    }

}

//val userInfo = profilelist[position]
//if (userInfo != null) {
//    holder.profileimg.setImageResource(userInfo.profileImg)
//} else {
//    holder.profileimg.setOnClickListener {
//        if (position == 0) {
//            val intent = Intent(it.context, MyPage::class.java)
//            intent.putExtra("position", position)
//            it.context.startActivity(intent)
//        } else {
//            val intent = Intent(it.context, DetailPage::class.java)
//            intent.putExtra("position", position)
//            it.context.startActivity(intent)
//        }
//    }
//}
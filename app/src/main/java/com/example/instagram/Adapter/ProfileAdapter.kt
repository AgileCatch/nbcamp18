package com.example.instagram.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.Userinfo
import com.example.instagram.Data.UserinfoSingleton
import com.example.instagram.DetailPage
import com.example.instagram.MyPage
import com.example.instagram.R

class ProfileAdapter(val profilelist: ArrayList<Userinfo>) :
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

        if (userInfo.changedProfileImg == Uri.EMPTY) {
            holder.profileimg.setImageResource(userInfo.profileImg)
        } else {
            holder.profileimg.setImageURI(userInfo.changedProfileImg)
        }

        holder.profileimg.setOnClickListener {
            if(position == 0){
                val intent = Intent(it.context, MyPage::class.java)
                intent.putExtra("position", position)
                it.context.startActivity(intent)
            }else{
                val intent = Intent(it.context, DetailPage::class.java)
                updateData(position)
                intent.putExtra("position", position)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return profilelist.size
    }

    fun updateData(position: Int) {
        val userinfoList = UserinfoSingleton.getUserinfoList()
        val userinfo = userinfoList[position]

        UserinfoSingleton.todayIncrease(userinfo)
    }

}
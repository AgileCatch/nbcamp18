package com.example.instagram.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.Comment
import com.example.instagram.Data.Profile
import com.example.instagram.Data.Userinfo
import com.example.instagram.Data.UserinfoSingleton
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
        val userProfile = profilelist[position]
        if (userProfile != null) {
            holder.profileimg.setImageResource(userProfile.profileImg)

            holder.profileimg.setOnClickListener {
                val intent = Intent(it.context, MyPage::class.java)
                val userinfoList = UserinfoSingleton.getUserinfoList()
                val userinfo = userinfoList[0]

                UserinfoSingleton.todayIncrease(userinfo)

                intent.putExtra("num", 0)
                it.context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return profilelist.size
    }

}
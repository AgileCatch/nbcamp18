package com.example.instagram.Adapter

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.instagram.Data.Comment
import com.example.instagram.R

class EditCommentAdapter(var commentList:ArrayList<Comment>) : RecyclerView.Adapter<EditCommentAdapter.CustomViewHolder>(){

    class CustomViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.lv_name)
        val nicname = itemView.findViewById<TextView>(R.id.lv_nicname)
        val profileimg = itemView.findViewById<ImageView>(R.id.lv_profile)
        val datetime = itemView.findViewById<TextView>(R.id.datetime)
        val content =itemView.findViewById<TextView>(R.id.lv_comment)
        val btn_delete = itemView.findViewById<Button>(R.id.bt_delete)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_edit_comment_recycler, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.name.text=commentList.get(position).name
        holder.nicname.text = commentList.get(position).nicname
        holder.profileimg.setImageResource(commentList.get(position).profileimg)
        holder.content.text = commentList.get(position).content

        val commentDate = commentList[position].datetime // 댓글의 날짜 데이터 가져오기
        holder.datetime.text = commentDate // 날짜를 표시

        holder.btn_delete.setOnClickListener{
            val alertDialogBuilder = AlertDialog.Builder(it.context)
            alertDialogBuilder.setTitle("안내")
            val messageTextView = TextView(it.context)
            messageTextView.text = "일촌평을 삭제하시겠습니까?"
            messageTextView.gravity = Gravity.CENTER // 중앙 정렬을 설정합니다.

            messageTextView.setPadding(0, 100, 0, 50)
            messageTextView.setBackgroundColor(Color.WHITE)

            alertDialogBuilder.setView(messageTextView)
            alertDialogBuilder.setIcon(R.drawable.baseline_notifications_none_24)

            alertDialogBuilder.setPositiveButton("예") { _, _ ->
                val clickedItem = commentList[position]
                commentList.remove(clickedItem)  // 아이템 삭제
                notifyItemRemoved(position)
            }

            alertDialogBuilder.setNegativeButton("아니오") { _, _ ->
                Toast.makeText(it.context, "취소되었습니다", Toast.LENGTH_SHORT).show()
            }

            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()

        }
    }

    override fun getItemCount(): Int {
        return commentList.size
    }

}
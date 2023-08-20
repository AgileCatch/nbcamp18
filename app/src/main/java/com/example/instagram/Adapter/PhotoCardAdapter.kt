package com.example.instagram.Adapter

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
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
        holder.photo.setImageURI(photocardList[position].imageUri)
        holder.content.text = photocardList.get(position).content//내용

        val commentDate = photocardList[position].datetime // 사진의 날짜 데이터 가져오기
        holder.content_datetime.text = commentDate // 날짜를 표시

        // 수정 버튼 리스너
        holder.itemView.findViewById<Button>(R.id.bt_edit).setOnClickListener {
            openEditDialog(holder.itemView.context, position)
        }

        // 삭제 버튼 리스너
        holder.itemView.findViewById<Button>(R.id.bt_delete).setOnClickListener {
            val clickedItem = photocardList[position]
            showDeleteConfirmation(holder.itemView.context, position) //삭제 함수 호출
        }
    }

    // 수정 다이얼로그 열기
    private fun openEditDialog(context: Context, position: Int) {
        val dialogView = LayoutInflater.from(context).inflate(R.layout.dialog_edit_photo, null)
        val updatedTitle = dialogView.findViewById<EditText>(R.id.edit_dialog_title)
        val updatedContent = dialogView.findViewById<EditText>(R.id.edit_dialog_content)
        val saveButton = dialogView.findViewById<Button>(R.id.btn_dialog_save)
        val cancelButton = dialogView.findViewById<Button>(R.id.btn_dialog_cancel)

        // 기존 데이터로 입력 필드 초기화
        updatedTitle.setText(photocardList[position].title)
        updatedContent.setText(photocardList[position].content)

        val dialogBuilder = AlertDialog.Builder(context)
            .setView(dialogView)
            .setTitle("수정하기")

        val alertDialog = dialogBuilder.create()

        // 저장 버튼 클릭 리스너
        saveButton.setOnClickListener {
            val newTitle = updatedTitle.text.toString()
            val newContent = updatedContent.text.toString()

            performEditAction(position, newTitle, newContent)

            alertDialog.dismiss()
        }

        // 취소 버튼 클릭 리스너
        cancelButton.setOnClickListener {
            alertDialog.dismiss()
        }

        alertDialog.show()
    }

    //삭제 다이얼로그
    @SuppressLint("SuspiciousIndentation")
    private fun showDeleteConfirmation(context: Context, position: Int) {
        val dialogBuilder = AlertDialog.Builder(context)
            dialogBuilder.setTitle("안내")
        val messageTextView = TextView(context)
        messageTextView.text = "글을 삭제하시겠습니까?"
        messageTextView.gravity = Gravity.CENTER // 중앙 정렬을 설정합니다.

        messageTextView.setPadding(0, 100, 0, 50)
        messageTextView.setBackgroundColor(Color.WHITE)

        dialogBuilder.setView(messageTextView)
        dialogBuilder.setIcon(R.drawable.baseline_notifications_none_24)

        dialogBuilder.setPositiveButton("예") { _, _ ->
            DeleteCard(position)
        }

        dialogBuilder.setNegativeButton("아니오") { _, _ ->
            Toast.makeText(context, "취소되었습니다", Toast.LENGTH_SHORT).show()
        }

        val alertDialog = dialogBuilder.create()
        alertDialog.show()
    }

    private fun performEditAction(position: Int, updatedTitle: String, updatedContent: String) {
        val item = photocardList[position]
        item.title = updatedTitle
        item.content = updatedContent

        // 어댑터 갱신
        notifyItemChanged(position)
    }
    override fun getItemCount(): Int {
        return photocardList.size
    }

    //삭제 작업 담당 함수
    private fun DeleteCard(position: Int) {
        val removedPhotoCard = photocardList.removeAt(position) // 아이템 삭제 및 삭제된 아이템 가져오기
        DataManager.removePhotoCard(removedPhotoCard) // DataManager에서 삭제

        notifyItemRemoved(position) // 삭제 후 화면 갱신하기
    }

}
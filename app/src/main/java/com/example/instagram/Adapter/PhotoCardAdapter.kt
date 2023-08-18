package com.example.instagram.Adapter

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
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

        // 수정 버튼 리스너
        holder.itemView.findViewById<Button>(R.id.bt_edit).setOnClickListener {
            openEditDialog(holder.itemView.context, position)
        }

        // 삭제 버튼 리스너
        holder.itemView.findViewById<Button>(R.id.bt_delete).setOnClickListener {
            val clickedItem = photocardList[position]
            DeleteCard(clickedItem, position) //삭제 함수 호출
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
    private fun DeleteCard(item: PhotoCard, position: Int) {
        photocardList.remove(item)  // 아이템 삭제
        notifyItemRemoved(position) // 삭제 후 화면 갱신하기
    }

}
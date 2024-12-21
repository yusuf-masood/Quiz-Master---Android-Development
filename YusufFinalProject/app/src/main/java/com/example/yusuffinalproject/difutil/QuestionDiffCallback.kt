package com.example.yusuffinalproject.difutil

import androidx.recyclerview.widget.DiffUtil
import com.example.yusuffinalproject.apiquestions.Question

class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
    override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean = oldItem == newItem
}
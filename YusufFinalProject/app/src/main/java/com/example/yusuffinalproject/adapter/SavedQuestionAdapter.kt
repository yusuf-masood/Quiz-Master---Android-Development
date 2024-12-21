package com.example.yusuffinalproject.adapter

import com.example.yusuffinalproject.questionroomDB.QuestionEntity


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.yusuffinalproject.R


class SavedQuestionAdapter(private val questions: List<QuestionEntity>) : RecyclerView.Adapter<SavedQuestionAdapter.SavedQuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedQuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_save_question, parent, false)
        return SavedQuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: SavedQuestionViewHolder, position: Int) {
        val question = questions[position]
        holder.bind(question)
    }

    override fun getItemCount(): Int {
        return questions.size
    }

    class SavedQuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvQuestionText: TextView = itemView.findViewById(R.id.tvQuestionText)
        private val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)
        private val tvDifficulty: TextView = itemView.findViewById(R.id.tvDifficulty)

        fun bind(question: QuestionEntity) {
            tvQuestionText.text = question.text
            tvCategory.text = question.category
            tvDifficulty.text = question.difficulty
        }
    }
}

package com.example.yusuffinalproject.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.yusuffinalproject.R
import com.example.yusuffinalproject.apiquestions.Question
import com.example.yusuffinalproject.difutil.QuestionDiffCallback
class QuestionAdapter(private val onQuestionClicked: (Question) -> Unit) :
    ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_question, parent, false)
        return QuestionViewHolder(view)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position), onQuestionClicked)
    }

    class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val questionText: TextView = itemView.findViewById(R.id.tvQuestion)
        private val correctAnswer: TextView = itemView.findViewById(R.id.tvCorrectAnswer)

        fun bind(question: Question, onQuestionClicked: (Question) -> Unit) {
            questionText.text = question.question.text
            correctAnswer.text = "Correct Answer: ${question.correctAnswer}"

            itemView.setOnClickListener {
                onQuestionClicked(question)
            }
        }
    }
}

package com.example.yusuffinalproject.apiquestions

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val id: String,
    val category: String,
    val correctAnswer: String,
    val incorrectAnswers: List<String>,
    val question: QuestionText,
    val difficulty: String
) : Parcelable {

    @Parcelize
    data class QuestionText(val text: String) : Parcelable
}
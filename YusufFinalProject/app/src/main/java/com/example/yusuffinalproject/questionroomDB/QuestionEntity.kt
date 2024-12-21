package com.example.yusuffinalproject.questionroomDB

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val correctAnswer: String,
    val category: String,
    val difficulty: String
)

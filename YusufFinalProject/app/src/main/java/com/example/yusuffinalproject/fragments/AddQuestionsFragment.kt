package com.example.yusuffinalproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.lifecycleScope
import com.example.yusuffinalproject.R
import com.example.yusuffinalproject.questionroomDB.QuestionDatabase
import com.example.yusuffinalproject.questionroomDB.QuestionEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class AddQuestionsFragment : Fragment() {

    private lateinit var etQuestionText: EditText
    private lateinit var etCorrectAnswer: EditText
    private lateinit var etCategory: EditText
    private lateinit var etDifficulty: EditText
    private lateinit var btnSaveQuestion: Button
    private lateinit var db: QuestionDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_questions, container, false)

        // Initialize views
        etQuestionText = view.findViewById(R.id.etQuestionText)
        etCorrectAnswer = view.findViewById(R.id.etCorrectAnswer)
        etCategory = view.findViewById(R.id.etCategory)
        etDifficulty = view.findViewById(R.id.etDifficulty)
        btnSaveQuestion = view.findViewById(R.id.btnSaveQuestion)

        // Initialize Room Database
        db = QuestionDatabase.getDatabase(requireContext())

        // Set button click listener
        btnSaveQuestion.setOnClickListener {
            saveQuestionToDatabase()
        }

        return view
    }

    private fun saveQuestionToDatabase() {
        // Get data from the EditTexts
        val questionText = etQuestionText.text.toString()
        val correctAnswer = etCorrectAnswer.text.toString()
        val category = etCategory.text.toString()
        val difficulty = etDifficulty.text.toString()

        // Create a QuestionEntity object
        val questionEntity = QuestionEntity(
            text = questionText,
            correctAnswer = correctAnswer,
            category = category,
            difficulty = difficulty
        )

        // Save to the database using a coroutine
        lifecycleScope.launch(Dispatchers.IO) {
            db.questionDao().insertQuestion(questionEntity)
            // Optionally, show a success message or clear the input fields
        }
    }
}
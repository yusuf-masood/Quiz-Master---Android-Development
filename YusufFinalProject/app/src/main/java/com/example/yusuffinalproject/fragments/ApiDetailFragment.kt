package com.example.yusuffinalproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.yusuffinalproject.R
import com.example.yusuffinalproject.apiquestions.Question


import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.example.yusuffinalproject.questionroomDB.QuestionDatabase
import com.example.yusuffinalproject.questionroomDB.QuestionEntity

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApiDetailFragment : Fragment() {

    private var question: Question? = null
    private lateinit var db: QuestionDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_api_detail, container, false)

        // Initialize Room Database with the new name
        db = QuestionDatabase.getDatabase(requireContext())

        // Retrieve the question object from the arguments
        question = arguments?.getParcelable("question")

        // Populate UI with question details
        view.findViewById<TextView>(R.id.tvDetailQuestionText).text = question?.question?.text
        view.findViewById<TextView>(R.id.tvDetailCorrectAnswer).text = question?.correctAnswer
        view.findViewById<TextView>(R.id.tvDetailCategory).text = question?.category
        view.findViewById<TextView>(R.id.tvDetailDifficulty).text = question?.difficulty

        // Set up the save button to insert the question into the database
        view.findViewById<Button>(R.id.btnSaveQuestion).setOnClickListener {
            saveQuestionToDatabase()
        }

        return view
    }

    private fun saveQuestionToDatabase() {
        // Ensure question data is not null
        val questionEntity = QuestionEntity(
            text = question?.question?.text ?: "",
            correctAnswer = question?.correctAnswer ?: "",
            category = question?.category ?: "",
            difficulty = question?.difficulty ?: ""
        )

        // Save to the database using a coroutine on the IO thread
        lifecycleScope.launch(Dispatchers.IO) {
            db.questionDao().insertQuestion(questionEntity)
        }
    }
}

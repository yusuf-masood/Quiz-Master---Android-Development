package com.example.yusuffinalproject.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yusuffinalproject.R
import com.example.yusuffinalproject.adapter.SavedQuestionAdapter
import com.example.yusuffinalproject.questionroomDB.QuestionDatabase



import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SaveQuestionFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var savedQuestionAdapter: SavedQuestionAdapter
    private lateinit var db: QuestionDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_save_question, container, false)

        // Initialize Room Database
        db = QuestionDatabase.getDatabase(requireContext())

        // Set up RecyclerView
        recyclerView = view.findViewById(R.id.recyclerViewQuestions)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Fetch the list of questions and set the adapter
        fetchQuestions()

        return view
    }

    private fun fetchQuestions() {
        lifecycleScope.launch(Dispatchers.IO) {
            val questions = db.questionDao().getAllQuestions()  // Ensure this method is in your DAO
            lifecycleScope.launch(Dispatchers.Main) {
                // Set the adapter with the fetched questions
                savedQuestionAdapter = SavedQuestionAdapter(questions)
                recyclerView.adapter = savedQuestionAdapter
            }
        }
    }
}

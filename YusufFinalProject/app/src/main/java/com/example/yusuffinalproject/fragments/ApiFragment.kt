package com.example.yusuffinalproject.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.yusuffinalproject.R
import com.example.yusuffinalproject.adapter.QuestionAdapter
import com.example.yusuffinalproject.apiquestions.ApiClient
import com.example.yusuffinalproject.apiquestions.Question
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ApiFragment : Fragment() {
    private lateinit var adapter: QuestionAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        adapter = QuestionAdapter { question ->
            // Open detail fragment manually
            openDetailFragment(question)
        }
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Fetch and display data
        fetchTriviaQuestions()
    }

    private fun fetchTriviaQuestions() {
        lifecycleScope.launch {
            try {
                val questions = withContext(Dispatchers.IO) { ApiClient.service.getQuestions() }
                adapter.submitList(questions)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun openDetailFragment(question: Question) {
        // Create an instance of ApiDetailFragment and pass the question as an argument
        val fragment = ApiDetailFragment()
        val bundle = Bundle().apply {
            putParcelable("question", question)
        }
        fragment.arguments = bundle

        // Replace the current fragment with the detail fragment
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, fragment) // Replace 'fragment_container' with your container ID
            .addToBackStack(null) // Add the transaction to the back stack
            .commit()
    }
}

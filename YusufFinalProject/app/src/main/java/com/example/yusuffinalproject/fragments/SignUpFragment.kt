package com.example.yusuffinalproject.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.yusuffinalproject.R
import com.example.yusuffinalproject.roomDB.AppDatabase
import com.example.yusuffinalproject.roomDB.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignUpFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_sign_up, container, false)

        view.findViewById<Button>(R.id.btnSignUp).setOnClickListener {
            val username = view.findViewById<EditText>(R.id.etUsername)?.text.toString().trim()
            val email = view.findViewById<EditText>(R.id.etEmail)?.text.toString().trim()
            val password = view.findViewById<EditText>(R.id.etPassword)?.text.toString().trim()

            if (username.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty()) {
                val user = User(username = username, email = email, password = password)
                lifecycleScope.launch {
                    withContext(Dispatchers.IO) {
                        AppDatabase.getInstance(requireContext()).userDao().insertUser(user)
                    }
                    if (isAdded) {
                        Toast.makeText(context, "User registered!", Toast.LENGTH_SHORT).show()
                        navigateToSignIn()
                    }
                }
            } else {
                Toast.makeText(context, "All fields are required!", Toast.LENGTH_SHORT).show()
            }
        }


        return view
    }

    private fun navigateToSignIn() {
        val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container, SignInFragment()) // Replace with your container ID
        fragmentTransaction.commit()
    }
}

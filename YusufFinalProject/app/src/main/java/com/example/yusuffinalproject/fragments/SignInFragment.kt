package com.example.yusuffinalproject.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.yusuffinalproject.HomeActivity
import com.example.yusuffinalproject.R
import com.example.yusuffinalproject.roomDB.AppDatabase
import kotlinx.coroutines.launch


class SignInFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view= inflater.inflate(R.layout.fragment_sign_in, container, false)

        view.findViewById<Button>(R.id.btnSignIn).setOnClickListener {
            val email = view.findViewById<EditText>(R.id.etEmail).text.toString().trim()
            val password = view.findViewById<EditText>(R.id.etPassword).text.toString().trim()


            if (email.isNotEmpty() && password.isNotEmpty()) {
                lifecycleScope.launch {
                    val user = AppDatabase.getInstance(requireContext())
                        .userDao().loginUser(email, password)

                    if (user != null) {
                        // Navigate to HomeActivity
                        val intent =    Intent(requireContext(), HomeActivity::class.java)
                        intent.putExtra("username", user.username) // Pass username if needed
                        startActivity(intent)

                        // Optionally, finish the current activity to prevent returning to SignInFragment
                        requireActivity().finish()
                    } else {
                        Toast.makeText(context, "Invalid credentials!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(context, "All fields are required!", Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<TextView>(R.id.tvSignUpLink).setOnClickListener {
            // Replace SignInFragment with SignUpFragment
            val fragmentTransaction = requireActivity().supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, SignUpFragment())
            fragmentTransaction.addToBackStack(null) // Optionally, add to back stack
            fragmentTransaction.commit()
        }

        return view
    }
}

package ru.dubinin.application.fragment.enter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.launch
import ru.dubinin.application.R
import ru.dubinin.application.fragment.MainFragment
import ru.dubinin.application.service.UserService

class EnterFragmentForm: Fragment(R.layout.enter_form) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val returnButton = view.findViewById<Button>(R.id.back_to_enter)
        val authButton = view.findViewById<Button>(R.id.enter_button)
        val progressBar = view.findViewById<ProgressBar>(R.id.enter_progress_bar)

        val editTextPhone = view.findViewById<EditText>(R.id.phone_number)
        val editTextPassword = view.findViewById<EditText>(R.id.password)

        returnButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, EnterFragment())
                .commit()
        }

        authButton.setOnClickListener {
            progressBar.visibility = View.VISIBLE
            val phone = editTextPhone.text.toString()
            val password = editTextPassword.text.toString()
            lifecycle.coroutineScope.launch {
                val user = UserService.getUser(requireContext(), phone, password)
                if (user != null) {
                    parentFragmentManager.beginTransaction()
                        .replace(R.id.container, MainFragment(user))
                        .commit()
                } else {
                    val err = Toast.makeText(
                        requireActivity().applicationContext,
                        "Unknown User",
                        Toast.LENGTH_SHORT
                    )
                    err.show()
                }
                progressBar.visibility = View.INVISIBLE
            }
        }
    }
}
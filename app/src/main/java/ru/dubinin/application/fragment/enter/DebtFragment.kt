package ru.dubinin.application.fragment.enter

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import ru.dubinin.application.R
import ru.dubinin.application.entity.User

class DebtFragment(val user: User): Fragment(R.layout.debt_screen) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val returnButton = view.findViewById<TextView>(R.id.return_button)
        returnButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment(user))
                .commit()
        }
    }
}
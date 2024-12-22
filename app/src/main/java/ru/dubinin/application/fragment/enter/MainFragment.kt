package ru.dubinin.application.fragment.enter

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.dubinin.application.R
import ru.dubinin.application.entity.User

class MainFragment(val user: User): Fragment(R.layout.main_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.next)

        button.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, DebtFragment(user))
                .commit()
        }
    }
}
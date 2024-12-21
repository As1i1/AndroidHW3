package ru.dubinin.application.fragment.enter

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.fragment.app.Fragment
import ru.dubinin.application.R

class EnterFragment: Fragment(R.layout.enter) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val button = view.findViewById<Button>(R.id.enter_button)

        button.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, EnterFragmentForm())
                .commit()
        }
    }

}
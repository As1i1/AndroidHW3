package ru.dubinin.application.fragment

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.google.gson.Gson
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import ru.dubinin.application.R
import ru.dubinin.application.entity.User
import ru.dubinin.application.fragment.enter.EnterFragment
import java.lang.Exception

class ProfileFragment(val user: User): Fragment(R.layout.profile) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val exitButton = view.findViewById<View>(R.id.exit)
        exitButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, EnterFragment())
                .commit()
        }

        val debt1 = view.findViewById<View>(R.id.debt_i)
        val debt2 = view.findViewById<View>(R.id.debt_t)
        debt1.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment(user))
                .commit()
        }
        debt2.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment(user))
                .commit()
        }

        val profileName = view.findViewById<TextView>(R.id.profile_name)
        profileName.text = user.name

        val profileContact = view.findViewById<TextView>(R.id.contact)
        profileContact.text = "${user.phoneNumber} (${user.bankName})"

        val image = view.findViewById<ImageView>(R.id.image_icon)
        val progressBar = view.findViewById<ProgressBar>(R.id.debt_progress_bar)
        lifecycleScope.launch {
            if (user.image == null) {
                progressBar.visibility = View.INVISIBLE
                return@launch
            }
            Picasso.get().load(user.image).into(image, object : Callback {
                override fun onSuccess() {
                    progressBar.visibility = View.INVISIBLE
                }

                override fun onError(e: Exception?) {
                    progressBar.visibility = View.INVISIBLE
                    val err = Toast.makeText(
                        requireActivity().applicationContext,
                        "Server error",
                        Toast.LENGTH_SHORT
                    )
                    err.show()
                }

            })
        }

        val shareButton = view.findViewById<Button>(R.id.share_button)
        shareButton.setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, Gson().toJson(user))
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)
        }
    }
}
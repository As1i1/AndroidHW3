package ru.dubinin.application.fragment

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.dubinin.application.R
import ru.dubinin.application.entity.User
import ru.dubinin.application.service.DebtService
import ru.dubinin.application.service.UserService

class MainFragment(private val user: User): Fragment(R.layout.main_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recycler = view.findViewById<RecyclerView>(R.id.debts_rv)
        val adapter = DebtsAdapter(parentFragmentManager, user)
        recycler.adapter = adapter

        val profile1 = view.findViewById<View>(R.id.profile_i)
        val profile2 = view.findViewById<View>(R.id.profile_t)
        profile1.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ProfileFragment(user))
                .commit()
        }
        profile2.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, ProfileFragment(user))
                .commit()
        }

        val progressBar = view.findViewById<ProgressBar>(R.id.debt_progress_bar)
        lifecycleScope.launch {
            try {
                val debts = DebtService.getDebts(user.id) ?: return@launch
                val ownerIds = debts.map { it.owner_id }
                val userById = ownerIds.map { UserService.getUserById(requireContext(), it) }
                    .associateBy { it!!.id }
                adapter.initDebts(debts.map { it to userById[it.owner_id]!! })
            } finally {
                progressBar.visibility = View.INVISIBLE
            }
        }
    }
}
package ru.dubinin.application.fragment.debt

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import ru.dubinin.application.R
import ru.dubinin.application.entity.Debt
import ru.dubinin.application.entity.User
import ru.dubinin.application.fragment.MainFragment
import ru.dubinin.application.service.DebtService

class DebtFragment(val user: User, val debt: Debt): Fragment(R.layout.debt_screen) {

    val adapter = DebtItemAdapter(lifecycleScope)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val title = view.findViewById<TextView>(R.id.debt_name)
        title.text = debt.title

        val summary = view.findViewById<TextView>(R.id.debt_summary)
        summary.text = "${debt.summary} руб."

        val payButton = view.findViewById<Button>(R.id.button_pay_waited)
        val successPay = view.findViewById<Button>(R.id.button_pay_success)
        payButton.setOnClickListener {
            payButton.visibility = View.INVISIBLE
            successPay.visibility = View.VISIBLE
        }

        val recyclerView = view.findViewById<RecyclerView>(R.id.debt_items_rv)
        recyclerView.adapter = adapter

        val returnButton = view.findViewById<TextView>(R.id.return_button)
        returnButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment(user))
                .commit()
        }

        val authorContacts = view.findViewById<TextView>(R.id.phone_bank)
        val progressBar = view.findViewById<ProgressBar>(R.id.debt_progress_bar)
        lifecycleScope.launch {
            try {
                val items = DebtService.getItemsDebt(debt.id)

                items?.let { l -> adapter.addItems(l.map { it.name to it.summary }) } ?: run {
                    val err = Toast.makeText(
                        requireActivity().applicationContext,
                        "Unknown Debt",
                        Toast.LENGTH_SHORT
                    )
                    err.show()
                }
                progressBar.visibility = View.INVISIBLE
            } catch (e: Exception) {
                val err = Toast.makeText(
                    requireActivity().applicationContext,
                    "Server error",
                    Toast.LENGTH_SHORT
                )
                err.show()
            }
        }
    }
}
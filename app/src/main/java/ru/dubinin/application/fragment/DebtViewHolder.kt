package ru.dubinin.application.fragment

import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ru.dubinin.application.R
import ru.dubinin.application.entity.Debt
import ru.dubinin.application.entity.User
import ru.dubinin.application.fragment.debt.DebtFragment

class DebtViewHolder(val user: User, val view: View) : ViewHolder(view) {

    private var debt: Debt? = null
    private var author: User? = null
    private val debtName = view.findViewById<TextView>(R.id.debt_name)
    private val debtAuthor = view.findViewById<TextView>(R.id.debt_author)
    private val debtGuest = view.findViewById<TextView>(R.id.debt_guest_count)
    private val summary = view.findViewById<TextView>(R.id.debt_summary)
    private val debtDate = view.findViewById<TextView>(R.id.debt_date)

    fun bind(debt: Debt, author: User) {
        this.debt = debt
        this.author = author
        debtName.text = debt.name
        debtAuthor.text = author.name
        debtGuest.text = debt.guests.toString()
        summary.text = debt.summary.toString()
        debtDate.text = debt.date
    }

    fun setClickListener(transactionFragmentManager: FragmentManager) {
        view.setOnClickListener {
            debt?.let {
                transactionFragmentManager.beginTransaction()
                    .replace(R.id.container, DebtFragment(user, it, author!!))
                    .commit()
            } ?: run {
                val err = Toast.makeText(
                    view.context.applicationContext,
                    "Unknown Debt",
                    Toast.LENGTH_SHORT
                )
                err.show()
            }
        }

    }
}
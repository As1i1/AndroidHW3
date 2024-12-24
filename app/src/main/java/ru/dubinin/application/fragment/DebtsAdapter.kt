package ru.dubinin.application.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import ru.dubinin.application.R
import ru.dubinin.application.entity.Debt
import ru.dubinin.application.entity.User

class DebtsAdapter(
    val transactionManager: FragmentManager,
    val user: User,
    ) : RecyclerView.Adapter<DebtViewHolder>() {

    private var debts = mutableListOf<Debt>()
        set(value) {
            debts.clear()
            debts.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtViewHolder {
        val viewHolder = DebtViewHolder(
            user,
            LayoutInflater.from(parent.context).inflate(R.layout.main_fragment_item, parent, false)
        )
        viewHolder.setClickListener(transactionManager)

        return viewHolder
    }

    override fun getItemCount(): Int = debts.size

    override fun onBindViewHolder(holder: DebtViewHolder, position: Int) {
        holder.bind(debts[position])
    }

    fun initDebts(debts: List<Debt>) {
        this.debts = debts.toMutableList()
    }
}
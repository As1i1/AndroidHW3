package ru.dubinin.application.fragment.enter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.dubinin.application.R

class DebtItemViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val positionName = view.findViewById<TextView>(R.id.position_name)
    val summary = view.findViewById<TextView>(R.id.item_summary)

    fun bind(text: String, sum: Long) {
        positionName.text = text
        summary.text = "$sum руб."
    }
}
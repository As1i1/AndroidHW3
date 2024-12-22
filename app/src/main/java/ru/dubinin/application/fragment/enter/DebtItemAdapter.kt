package ru.dubinin.application.fragment.enter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import ru.dubinin.application.R

class DebtItemAdapter(private val coroutineScope: CoroutineScope)
    : RecyclerView.Adapter<DebtItemViewHolder>() {

    private var _items = mutableListOf<Pair<String, Long>>()
        set(value) {
            _items.clear()
            _items.addAll(value)
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DebtItemViewHolder =
        DebtItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.debt_item, parent, false)
        )

    override fun getItemCount(): Int {
        return _items.size
    }

    override fun onBindViewHolder(holder: DebtItemViewHolder, position: Int) {
        val (text, sum) = _items[position]
        holder.bind(text, sum)
    }

    fun addItems(items: List<Pair<String, Long>>) {
        _items = items.toMutableList()
    }
}
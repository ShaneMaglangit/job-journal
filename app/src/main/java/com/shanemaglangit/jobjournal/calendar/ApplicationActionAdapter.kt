package com.shanemaglangit.jobjournal.calendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shanemaglangit.jobjournal.data.ApplicationActions
import com.shanemaglangit.jobjournal.data.MarkerColor
import com.shanemaglangit.jobjournal.databinding.ApplicationActionsItemBinding
import com.shanemaglangit.jobjournal.util.formatToString
import java.time.LocalDate

class ApplicationActionListAdapter() :
    ListAdapter<Pair<LocalDate, String>, ApplicationActionListAdapter.ViewHolder>(PairDiffCallback()) {

    public override fun getItem(position: Int): Pair<LocalDate, String> {
        return super.getItem(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: ApplicationActionsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the items to the view holder
         */
        fun bind(item: Pair<LocalDate, String>) {
            binding.textDate.text = item.first.formatToString()
            binding.textAction.text = item.second
        }

        /**
         * Static method that is used as a form of factory method for creating a view holder instance
         */
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ApplicationActionsItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class PairDiffCallback : DiffUtil.ItemCallback<Pair<LocalDate, String>>() {
    override fun areContentsTheSame(oldItem: Pair<LocalDate, String>, newItem: Pair<LocalDate, String>): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: Pair<LocalDate, String>, newItem: Pair<LocalDate, String>): Boolean {
        return oldItem == newItem
    }
}
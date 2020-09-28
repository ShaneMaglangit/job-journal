package com.shanemaglangit.jobjournal.jobapplicationlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shanemaglangit.jobjournal.data.JobApplication
import com.shanemaglangit.jobjournal.databinding.JobApplicationItemBinding

class JobApplicationListAdapter :
    ListAdapter<JobApplication, JobApplicationListAdapter.ViewHolder>(JobApplicationDiffCallback()) {

    public override fun getItem(position: Int): JobApplication {
        return super.getItem(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: JobApplicationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the items to the view holder
         */
        fun bind(item: JobApplication) {
            binding.textCompany.text = item.company
            binding.textDescription.text = "Applied as a ${item.position}"
        }

        /**
         * Static method that is used as a form of factory method for creating a view holder instance
         */
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = JobApplicationItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}

class JobApplicationDiffCallback : DiffUtil.ItemCallback<JobApplication>() {
    override fun areContentsTheSame(oldItem: JobApplication, newItem: JobApplication): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: JobApplication, newItem: JobApplication): Boolean {
        return oldItem.id == newItem.id
    }
}
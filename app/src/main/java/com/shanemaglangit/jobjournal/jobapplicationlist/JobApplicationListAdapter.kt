package com.shanemaglangit.jobjournal.jobapplicationlist

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shanemaglangit.jobjournal.R
import com.shanemaglangit.jobjournal.data.JobApplication
import com.shanemaglangit.jobjournal.databinding.JobApplicationItemBinding
import com.shanemaglangit.jobjournal.util.Converter
import com.shanemaglangit.jobjournal.util.formatToString
import timber.log.Timber

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
            binding.textPosition.text = item.position
            binding.textDate.text = item.applicationDate?.formatToString()
            binding.textStatus.text = item.applicationStatus.name.toLowerCase().capitalize()
            binding.textDescription.text = if(!item.additionalNotes.isNullOrBlank()) item.additionalNotes else "Applied as ${item.position}"
            binding.textPhone.text = if(!item.phoneNumber.isNullOrBlank()) item.phoneNumber else "No phone number provided"
            binding.textEmail.text = if(!item.emailAddress.isNullOrBlank()) item.emailAddress else "No email address provided"

            binding.containerConstraint.setOnClickListener {
                binding.imageExpand.rotation = if(binding.imageExpand.rotation == 90F) 0F else 90F
                binding.textDate.toggleVisibility()
                binding.textEmail.toggleVisibility()
                binding.textPhone.toggleVisibility()
                binding.textPosition.toggleVisibility()
                binding.textStatus.toggleVisibility()
                binding.buttonDelete.toggleVisibility()
                binding.buttonEdit.toggleVisibility()
            }
        }

        private fun View.toggleVisibility() {
            this.visibility = if(this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
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
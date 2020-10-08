package com.shanemaglangit.jobjournal.jobapplicationlist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.shanemaglangit.jobjournal.R
import com.shanemaglangit.jobjournal.data.JobApplication
import com.shanemaglangit.jobjournal.data.MarkerColor
import com.shanemaglangit.jobjournal.databinding.JobApplicationItemBinding
import com.shanemaglangit.jobjournal.util.formatToString
import timber.log.Timber

class JobApplicationListAdapter(private val itemClickListener: JobApplicationListItemListener) :
    ListAdapter<JobApplication, JobApplicationListAdapter.ViewHolder>(JobApplicationDiffCallback()) {

    public override fun getItem(position: Int): JobApplication {
        return super.getItem(position)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, itemClickListener)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    class ViewHolder private constructor(private val binding: JobApplicationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        /**
         * Binds the items to the view holder
         */
        fun bind(item: JobApplication, itemClickListener: JobApplicationListItemListener) {
            // Put the contents of the item to the views
            binding.textCompany.text = item.company
            binding.textPosition.text = item.position
            binding.textDate.text = item.applicationDate?.formatToString()
            binding.textStatus.text = item.applicationStatus.name.toLowerCase().capitalize()
            binding.textDescription.text =
                if (!item.additionalNotes.isNullOrBlank()) item.additionalNotes else "Applied as ${item.position}"
            binding.textPhone.text =
                if (!item.phoneNumber.isNullOrBlank()) item.phoneNumber else "No phone number provided"
            binding.textEmail.text =
                if (!item.emailAddress.isNullOrBlank()) item.emailAddress else "No email address provided"

            // Set the color of the marker
            binding.imageExpand.setColorFilter(MarkerColor.getColor(binding.root.context, item.markerColor))

            // Expands the view to show the hidden details of the card
            binding.containerConstraint.setOnClickListener {
                binding.imageExpand.rotation = if (binding.imageExpand.rotation == 90F) 0F else 90F
                binding.textEmail.toggleVisibility()
                binding.textPhone.toggleVisibility()
                binding.textPosition.toggleVisibility()
                binding.textStatus.toggleVisibility()
                binding.buttonDelete.toggleVisibility()
                binding.buttonEdit.toggleVisibility()
            }

            // Add a listener for the edit button to modify the contents of the item
            binding.buttonEdit.setOnClickListener { itemClickListener.onEditClick(item) }

            // Add a listener for the delete button to delete the item from the database
            binding.buttonDelete.setOnClickListener { itemClickListener.onDeleteClick(item) }
        }

        /**
         * Toggles the visibility of the View
         */
        private fun View.toggleVisibility() {
            this.visibility = if (this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
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


class JobApplicationListItemListener(
    private val editClickListener: (jobApplication: JobApplication) -> Unit,
    private val deleteClickListener: (jobApplication: JobApplication) -> Unit
) {
    fun onEditClick(jobApplication: JobApplication) = editClickListener(jobApplication)
    fun onDeleteClick(jobApplication: JobApplication) = deleteClickListener(jobApplication)
}

class JobApplicationDiffCallback : DiffUtil.ItemCallback<JobApplication>() {
    override fun areContentsTheSame(oldItem: JobApplication, newItem: JobApplication): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: JobApplication, newItem: JobApplication): Boolean {
        return oldItem.id == newItem.id
    }
}
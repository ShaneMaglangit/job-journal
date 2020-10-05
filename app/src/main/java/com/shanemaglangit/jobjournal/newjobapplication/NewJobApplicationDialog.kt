package com.shanemaglangit.jobjournal.newjobapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.shanemaglangit.jobjournal.R
import com.shanemaglangit.jobjournal.databinding.DialogNewJobApplicationBinding
import com.shanemaglangit.jobjournal.util.showDatePickerDialogOnClick
import dagger.hilt.android.AndroidEntryPoint
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class NewJobApplicationDialog : DialogFragment() {
    private lateinit var binding: DialogNewJobApplicationBinding
    private val viewModel: NewJobApplicationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout and create the binding
        binding =
            DataBindingUtil.inflate(inflater, R.layout.dialog_new_job_application, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Add a listener to the home button for the action bar that dismisses the dialog
        binding.toolbar.setNavigationOnClickListener { this.dismiss() }

        // Add a listener to the add button on the toolbar
        binding.toolbar.setOnMenuItemClickListener {
            if (it.itemId == R.id.action_save) {
                viewModel.saveJobApplication()
                this.dismiss()
            }
            true
        }

        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set the dialog style
        setStyle(STYLE_NORMAL, R.style.AppTheme_FullScreenDialog)
    }

    override fun onStart() {
        super.onStart()
        // Resize the dialog to match parent
        val width = ViewGroup.LayoutParams.MATCH_PARENT
        val height = ViewGroup.LayoutParams.MATCH_PARENT
        dialog?.window?.setLayout(width, height)

        // Add animation for the transition
        dialog?.window?.setWindowAnimations(R.style.AppTheme_Slide);

        // Show a date picker dialog for the date edit text
        binding.editDate.showDatePickerDialogOnClick { _, year, month, day ->
            val calendar = Calendar.getInstance().apply { set(year, month, day) }
            binding.editDate.setText(SimpleDateFormat.getDateInstance().format(calendar.time))
            viewModel.setApplicationDate(LocalDateTime.ofInstant(calendar.toInstant(), ZoneOffset.UTC).toLocalDate())
        }
    }
}
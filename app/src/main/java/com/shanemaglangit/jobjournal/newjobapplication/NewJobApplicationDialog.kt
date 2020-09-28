package com.shanemaglangit.jobjournal.newjobapplication

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.shanemaglangit.jobjournal.R
import com.shanemaglangit.jobjournal.databinding.DialogNewJobApplicationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewJobApplicationDialog : DialogFragment() {
    private lateinit var binding: DialogNewJobApplicationBinding
    private val viewModel: NewJobApplicationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout and create the binding
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_new_job_application, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Add a listener to the home button for the action bar that dismisses the dialog
        binding.toolbar.setNavigationOnClickListener { dialog?.dismiss() }

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
    }
}
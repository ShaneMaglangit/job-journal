package com.shanemaglangit.jobjournal.newrecord

import android.app.Dialog
import android.os.Build
import android.os.Bundle
import android.view.*
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.shanemaglangit.jobjournal.R
import com.shanemaglangit.jobjournal.databinding.FragmentNewRecordDialogListDialogBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewRecordDialogFragment : DialogFragment() {
    private lateinit var binding: FragmentNewRecordDialogListDialogBinding
    private val viewModel: NewRecordDialogViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout and create the binding
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_new_record_dialog_list_dialog, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        // Add a listener to the home button for the action bar
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
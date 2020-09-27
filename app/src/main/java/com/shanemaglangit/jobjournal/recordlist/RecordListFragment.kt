package com.shanemaglangit.jobjournal.recordlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.shanemaglangit.jobjournal.R
import com.shanemaglangit.jobjournal.databinding.FragmentRecordListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecordListFragment : Fragment() {
    private lateinit var binding: FragmentRecordListBinding
    private val viewModel : RecordListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout and create the binding
        binding = FragmentRecordListBinding.inflate(inflater)

        return binding.root
    }
}
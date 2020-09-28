package com.shanemaglangit.jobjournal.jobapplicationlist

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.shanemaglangit.jobjournal.databinding.FragmentJobApplicationListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JobApplicationListFragment : Fragment() {
    private lateinit var binding: FragmentJobApplicationListBinding
    private val viewModel : JobApplicationListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout and create the binding
        binding = FragmentJobApplicationListBinding.inflate(inflater)

        return binding.root
    }
}
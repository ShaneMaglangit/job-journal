package com.shanemaglangit.jobjournal.jobapplicationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.shanemaglangit.jobjournal.databinding.FragmentJobApplicationListBinding
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class JobApplicationListFragment : Fragment() {
    private lateinit var binding: FragmentJobApplicationListBinding
    private lateinit var jobApplicationListAdapter: JobApplicationListAdapter
    private val viewModel: JobApplicationListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout and create the binding
        binding = FragmentJobApplicationListBinding.inflate(inflater)

        // Set up the recycler view components
        jobApplicationListAdapter = JobApplicationListAdapter(
            JobApplicationListItemListener(
                { Timber.i("Edit clicked") },
                { viewModel.deleteJobApplication(it) }
            )
        )
        binding.recyclerRecordList.adapter = jobApplicationListAdapter
        binding.recyclerRecordList.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // Attach an observer that updates the recycler view items
        viewModel.jobApplication.observe(viewLifecycleOwner, Observer {
            jobApplicationListAdapter.submitList(it)
        })
    }
}
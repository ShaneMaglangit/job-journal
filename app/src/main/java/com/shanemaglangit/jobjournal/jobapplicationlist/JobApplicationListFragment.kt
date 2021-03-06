package com.shanemaglangit.jobjournal.jobapplicationlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.shanemaglangit.jobjournal.R
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
                {
                    if(findNavController().currentDestination?.id == R.id.jobApplicationListFragment) {
                        findNavController().navigate(
                            JobApplicationListFragmentDirections.openNewJobApplicationDialog(
                                it.copy()
                            )
                        )
                    }
                },
                { viewModel.deleteJobApplication(it) }
            )
        )
        binding.recyclerRecordList.adapter = jobApplicationListAdapter
        binding.recyclerRecordList.layoutManager = LinearLayoutManager(requireContext())

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // Attach a listener that updates the items whenever a query is submitted or cleared
        binding.searchFilter.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                jobApplicationListAdapter.submitList(viewModel.filterItems(query ?: ""))
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if(query.isNullOrEmpty()) jobApplicationListAdapter.submitList(viewModel.filterItems())
                return true
            }
        })

        // Attach an observer that updates the recycler view items
        viewModel.jobApplication.observe(viewLifecycleOwner, {
            jobApplicationListAdapter.submitList(viewModel.filterItems())
        })
    }
}
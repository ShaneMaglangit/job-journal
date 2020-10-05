package com.shanemaglangit.jobjournal.jobapplicationlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import com.shanemaglangit.jobjournal.data.JobApplication
import kotlinx.coroutines.launch

class JobApplicationListViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao
) : ViewModel() {
    val jobApplication: LiveData<List<JobApplication>> = databaseDao.getAllJobApplication()

    private val _filteredJobApplication = MutableLiveData<List<JobApplication>>()
    val filteredJobApplication: LiveData<List<JobApplication>>
        get() = _filteredJobApplication

    private var filter: String? = null

    init {
        jobApplication.observeForever { applyFilter() }
    }

    fun filterItems(filter: String?) {
        this.filter = filter
        applyFilter()
    }

    private fun applyFilter() {
        _filteredJobApplication.value = when (this.filter) {
            null, "" -> jobApplication.value
            else -> jobApplication.value!!.filter { e ->
                e.company!!.contains(filter.toString(), true) ||
                e.position!!.contains(filter.toString(), true) ||
                e.additionalNotes?.contains(filter.toString(), true) ?: false
            }
        }
    }

    fun deleteJobApplication(jobApplication: JobApplication) {
        viewModelScope.launch { databaseDao.delete(jobApplication) }
    }
}
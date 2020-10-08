package com.shanemaglangit.jobjournal.jobapplicationlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import com.shanemaglangit.jobjournal.data.JobApplication
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber

class JobApplicationListViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao
) : ViewModel() {
    private var filter = ""
    val jobApplication: LiveData<List<JobApplication>> = databaseDao.getAllJobApplication()

    fun filterItems(filter: String = "") : List<JobApplication> {
        this.filter = filter
        return jobApplication.value!!.filter { e ->
            e.company!!.contains(filter, true) ||
            e.position!!.contains(filter, true) ||
            e.additionalNotes?.contains(filter, true) ?: false
        }
    }

    fun deleteJobApplication(jobApplication: JobApplication) {
        viewModelScope.launch { databaseDao.delete(jobApplication) }
    }
}
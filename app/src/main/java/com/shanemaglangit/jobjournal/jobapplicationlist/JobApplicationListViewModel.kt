package com.shanemaglangit.jobjournal.jobapplicationlist

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import com.shanemaglangit.jobjournal.data.JobApplication
import kotlinx.coroutines.launch

class JobApplicationListViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao
) : ViewModel() {
    val jobApplication: LiveData<List<JobApplication>> = databaseDao.getAllJobApplication()

    fun deleteJobApplication(jobApplication: JobApplication) {
        viewModelScope.launch { databaseDao.delete(jobApplication) }
    }
}
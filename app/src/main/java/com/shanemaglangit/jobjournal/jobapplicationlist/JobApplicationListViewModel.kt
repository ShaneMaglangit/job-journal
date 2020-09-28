package com.shanemaglangit.jobjournal.jobapplicationlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import com.shanemaglangit.jobjournal.data.JobApplication

class JobApplicationListViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao
) : ViewModel() {
    val jobApplication: LiveData<List<JobApplication>> = databaseDao.getAllJobApplication()
}
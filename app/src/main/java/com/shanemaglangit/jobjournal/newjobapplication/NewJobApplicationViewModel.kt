package com.shanemaglangit.jobjournal.newjobapplication

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import com.shanemaglangit.jobjournal.data.JobApplication

class NewJobApplicationViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao
) : ViewModel() {
    val jobApplication = MutableLiveData(JobApplication())
}
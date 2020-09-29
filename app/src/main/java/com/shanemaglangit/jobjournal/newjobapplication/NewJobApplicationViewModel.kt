package com.shanemaglangit.jobjournal.newjobapplication

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shanemaglangit.jobjournal.data.AppDatabase
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import com.shanemaglangit.jobjournal.data.JobApplication
import kotlinx.coroutines.*
import timber.log.Timber

class NewJobApplicationViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao
) : ViewModel() {
    val jobApplication = MutableLiveData(JobApplication())

    fun saveJobApplication() {
        viewModelScope.launch { databaseDao.insert(jobApplication.value!!) }
    }
}
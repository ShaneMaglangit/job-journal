package com.shanemaglangit.jobjournal.newjobapplication

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import com.shanemaglangit.jobjournal.data.JobApplication
import kotlinx.coroutines.launch

class NewJobApplicationViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val jobApplication = MutableLiveData(savedStateHandle.get("jobApplication") ?: JobApplication())

    fun saveJobApplication() = viewModelScope.launch { databaseDao.insert(jobApplication.value!!) }
}
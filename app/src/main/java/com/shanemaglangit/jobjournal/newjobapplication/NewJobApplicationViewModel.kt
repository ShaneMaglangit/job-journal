package com.shanemaglangit.jobjournal.newjobapplication

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import com.shanemaglangit.jobjournal.data.JobApplication
import com.squareup.inject.assisted.AssistedInject
import kotlinx.coroutines.*
import timber.log.Timber

class NewJobApplicationViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val jobApplication = MutableLiveData(savedStateHandle.get("jobApplication") ?: JobApplication())

    fun saveJobApplication() = viewModelScope.launch { databaseDao.insert(jobApplication.value!!) }
}
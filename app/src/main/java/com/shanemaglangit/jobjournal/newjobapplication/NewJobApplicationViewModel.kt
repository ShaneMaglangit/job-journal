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
import java.time.LocalDate
import java.util.*

class NewJobApplicationViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao,
    @Assisted val savedStateHandle: SavedStateHandle
) : ViewModel() {
    val jobApplication = MutableLiveData(savedStateHandle.get("jobApplication") ?: JobApplication())

    fun saveJobApplication() = viewModelScope.launch {
        val oldItem: JobApplication? = savedStateHandle.get("jobApplication")
        val currentItem = jobApplication.value!!

        if (oldItem == null || oldItem.applicationStatus != currentItem.applicationStatus) {
            currentItem.statusChanges[LocalDate.now()] =
                "Marked application to ${currentItem.applicationStatus} as ${currentItem.applicationStatus}"
        }

        databaseDao.insert(jobApplication.value!!)
    }
}
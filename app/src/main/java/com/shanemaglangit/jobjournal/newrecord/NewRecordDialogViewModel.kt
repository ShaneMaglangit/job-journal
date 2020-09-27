package com.shanemaglangit.jobjournal.newrecord

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import com.shanemaglangit.jobjournal.data.JobApplication

class NewRecordDialogViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao
) : ViewModel() {
    val jobApplication = MutableLiveData(JobApplication())
}
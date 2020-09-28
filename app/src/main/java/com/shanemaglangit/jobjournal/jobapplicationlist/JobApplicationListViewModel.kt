package com.shanemaglangit.jobjournal.jobapplicationlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.shanemaglangit.jobjournal.data.AppDatabaseDao


class JobApplicationListViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao
) : ViewModel() {

}
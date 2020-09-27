package com.shanemaglangit.jobjournal.recordlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.shanemaglangit.jobjournal.data.AppDatabaseDao


class RecordListViewModel @ViewModelInject constructor(
    private val databaseDao: AppDatabaseDao
) : ViewModel() {

}
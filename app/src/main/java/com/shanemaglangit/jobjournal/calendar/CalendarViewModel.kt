package com.shanemaglangit.jobjournal.calendar

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import com.shanemaglangit.jobjournal.data.AppDatabaseDao


class CalendarViewModel @ViewModelInject constructor(private val databaseDao: AppDatabaseDao) :
    ViewModel() {

}
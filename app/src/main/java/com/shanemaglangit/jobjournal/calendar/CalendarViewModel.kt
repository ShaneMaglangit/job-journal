package com.shanemaglangit.jobjournal.calendar

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import kotlinx.coroutines.launch
import java.time.LocalDate


class CalendarViewModel @ViewModelInject constructor(private val databaseDao: AppDatabaseDao) :
    ViewModel() {
}
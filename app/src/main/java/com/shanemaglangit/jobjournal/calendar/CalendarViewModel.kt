package com.shanemaglangit.jobjournal.calendar

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kizitonwose.calendarview.model.CalendarMonth
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import timber.log.Timber
import java.time.LocalDate

class CalendarViewModel @ViewModelInject constructor(private val databaseDao: AppDatabaseDao) :
    ViewModel() {
    private val _applicationActions = MutableLiveData<List<Pair<LocalDate, String>>>()
    val applicationActions: LiveData<List<Pair<LocalDate, String>>>
        get() = _applicationActions

    init {
        val tempList = mutableListOf<Pair<LocalDate, String>>()

        databaseDao.getApplicationActions().forEach { applicationActions ->
            tempList.addAll(applicationActions.getAllDatesWithAction())
        }

        tempList.sortBy { it.first }
        _applicationActions.value = tempList
    }

    fun getActionCount(localDate: LocalDate) : Int {
        return _applicationActions.value?.count { it.first == localDate } ?: 0
    }

    fun getApplicationActionsByMonth(calendarMonth: CalendarMonth) : List<Pair<LocalDate, String>> {
        val actions = applicationActions.value?.filter {
            it.first.year == calendarMonth.year && it.first.monthValue == calendarMonth.month
        }

        return actions ?: mutableListOf()
    }
}
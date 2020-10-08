package com.shanemaglangit.jobjournal.calendar

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kizitonwose.calendarview.model.CalendarMonth
import com.shanemaglangit.jobjournal.data.AppDatabaseDao
import kotlinx.coroutines.launch
import timber.log.Timber
import java.time.LocalDate

class CalendarViewModel @ViewModelInject constructor(private val databaseDao: AppDatabaseDao) :
    ViewModel() {
    private val _applicationActions = MutableLiveData<List<Pair<LocalDate, String>>>()
    val applicationActions: LiveData<List<Pair<LocalDate, String>>>
        get() = _applicationActions

    init {
        // Retrieve a list of all the application actions
        viewModelScope.launch {
            val tempList = mutableListOf<Pair<LocalDate, String>>()

            // Converts the job application instance into a Pair<LocalDate, String>
            databaseDao.getApplicationActions().forEach { applicationActions ->
                // Add all of the dates paired with its action into the tempList
                tempList.addAll(applicationActions.getAllDatesWithAction())
            }

            // Sort the tempList by the date
            tempList.sortBy { it.first }
            _applicationActions.value = tempList
        }
    }

    /**
     * Returns the count of actions done in a given date
     */
    fun getActionCount(localDate: LocalDate) : Int {
        return _applicationActions.value?.count { it.first == localDate } ?: 0
    }

    /**
     * Returns a list of job actions that occured within the given month
     */
    fun getApplicationActionsByMonth(calendarMonth: CalendarMonth) : List<Pair<LocalDate, String>> {
        val actions = applicationActions.value?.filter {
            it.first.year == calendarMonth.year && it.first.monthValue == calendarMonth.month
        }

        return actions ?: mutableListOf()
    }
}
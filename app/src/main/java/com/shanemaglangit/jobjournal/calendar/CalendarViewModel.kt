package com.shanemaglangit.jobjournal.calendar

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        databaseDao.getApplicationActions().observeForever { applicationActionsLists ->
            val tempList = mutableListOf<Pair<LocalDate, String>>()

            applicationActionsLists.forEach { applicationActions ->
                tempList.addAll(applicationActions.getAllDatesWithAction())
            }

            _applicationActions.value = tempList
        }
    }

    fun getActionCount(localDate: LocalDate) : Int {
        return _applicationActions.value?.count { it.first == localDate } ?: 0
    }
}
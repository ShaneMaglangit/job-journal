package com.shanemaglangit.jobjournal.data

import timber.log.Timber
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.HashMap

data class ApplicationActions(
    private var company: String? = null,
    private var applicationDate: LocalDate? = null,
    private var statusChanges: HashMap<LocalDateTime, String> = hashMapOf()
) {
    /**
     * Used to convert the application date and status changes into a single list of a date-to-action pair
     */
    fun getAllDatesWithAction() : List<Pair<LocalDate, String>> {
        val datesWithActions = mutableListOf<Pair<LocalDate, String>>()
        if(applicationDate != null) datesWithActions.add(Pair(applicationDate!!, "Applied to $company"))
        statusChanges.forEach { datesWithActions.add(Pair(it.key.toLocalDate(), it.value)) }
        return datesWithActions
    }
}
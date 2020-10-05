package com.shanemaglangit.jobjournal.data

import java.time.LocalDate
import java.util.HashMap

data class ApplicationActions(
    private var company: String? = null,
    private var applicationDate: LocalDate? = null,
    private var statusChanges: HashMap<LocalDate, String> = hashMapOf()
) {
    fun getAllDatesWithAction() : List<Pair<LocalDate, String>> {
        val datesWithActions = mutableListOf<Pair<LocalDate, String>>()
        if(applicationDate != null) datesWithActions.add(Pair(applicationDate!!, "Applied to $company"))
        statusChanges.forEach { datesWithActions.add(Pair(it.key, it.value)) }
        return datesWithActions
    }
}
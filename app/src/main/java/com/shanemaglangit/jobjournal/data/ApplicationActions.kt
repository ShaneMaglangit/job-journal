package com.shanemaglangit.jobjournal.data

import java.time.LocalDate
import java.util.HashMap

data class ApplicationActions(
    var applicationDate: LocalDate? = null,
    var statusChanges: HashMap<LocalDate, String> = hashMapOf()
) {
    fun getAllDates() : List<LocalDate> {
        // Create the initial list to put all of the dates
        val allDates = mutableListOf<LocalDate>()

        // Add the application date to the list if one is set
        if(applicationDate != null) allDates.add(applicationDate!!)

        // Get all the dates stored in the HashMap for status change and add the to the list
        allDates.addAll(statusChanges.keys)

        return allDates
    }
}
package com.shanemaglangit.jobjournal.data

import java.time.LocalDate
import java.util.HashMap

data class ApplicationActions(
    private var company: String? = null,
    private var applicationDate: LocalDate? = null,
    private var statusChanges: HashMap<LocalDate, String> = hashMapOf()
) {
    fun getAllDatesWithAction() : HashMap<LocalDate, String> {
        if(applicationDate != null) statusChanges[applicationDate!!] = "Applied to $company"
        return statusChanges
    }
}
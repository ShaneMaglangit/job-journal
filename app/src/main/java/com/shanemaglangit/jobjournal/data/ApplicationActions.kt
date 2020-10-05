package com.shanemaglangit.jobjournal.data

import java.time.LocalDate
import java.util.HashMap

data class ApplicationActions(
    var applicationDate: LocalDate? = null,
    var statusChanges: HashMap<LocalDate, String> = hashMapOf()
)
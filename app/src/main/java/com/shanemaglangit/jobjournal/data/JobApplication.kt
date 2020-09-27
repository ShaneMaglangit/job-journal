package com.shanemaglangit.jobjournal.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime
import java.util.*

@Entity
data class JobApplication(
    @PrimaryKey val id: Long = 0L,
    var company: String? = null,
    var position: String? = null,
    var phoneNumber: String? = null,
    var emailAddress: String? = null,
    var applicationDate: Date? = null,
    var applicationStatus: ApplicationStatus = ApplicationStatus.PENDING,
    var additionalNotes: String? = null,
    var dateModified: Date = Date()
)

package com.shanemaglangit.jobjournal.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class JobApplication(
    @PrimaryKey val id: Long,
    val company: String,
    val position: String,
    val phoneNumber: String?,
    val emailAddress: String?,
    val applicationDate: Date?,
    val applicationStatus: ApplicationStatus,
    val additionalNotes: String,
    val dateModified: Date
)

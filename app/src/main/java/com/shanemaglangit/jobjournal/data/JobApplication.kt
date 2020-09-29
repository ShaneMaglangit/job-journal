package com.shanemaglangit.jobjournal.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "job_application_table")
data class JobApplication (
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    var company: String? = null,
    var position: String? = null,
    var phoneNumber: String? = null,
    var emailAddress: String? = null,
    var applicationDate: Date? = null,
    var applicationStatus: ApplicationStatus = ApplicationStatus.PENDING,
    var additionalNotes: String? = null,
    var dateModified: Date = Date(),
) : Parcelable

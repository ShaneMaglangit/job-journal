package com.shanemaglangit.jobjournal.util

import androidx.room.TypeConverter
import com.shanemaglangit.jobjournal.data.ApplicationStatus
import java.util.*

class Converter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?) : Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromApplicationStatus(value: String?) : ApplicationStatus? {
        return value?.let { ApplicationStatus.valueOf(it) }
    }

    @TypeConverter
    fun applicationStatusToString(applicationStatus: ApplicationStatus) : String? {
        return applicationStatus.toString()
    }
}
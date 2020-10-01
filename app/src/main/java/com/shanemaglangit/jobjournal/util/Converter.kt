package com.shanemaglangit.jobjournal.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shanemaglangit.jobjournal.data.ApplicationStatus
import com.shanemaglangit.jobjournal.data.MarkerColor
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class Converter {
    @TypeConverter
    fun fromTimestampToDate(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?) : Long? {
        return date?.time?.toLong()
    }

    @TypeConverter
    fun fromTimestampToLocalDate(value: Long?): LocalDate? {
        return value?.let { LocalDateTime.ofInstant(Instant.ofEpochMilli(value), ZoneOffset.UTC).toLocalDate() }
    }

    @TypeConverter
    fun localDateToTimestamp(date: LocalDate?) : Long? {
        return date?.atStartOfDay()?.toInstant(ZoneOffset.UTC)?.toEpochMilli()
    }

    @TypeConverter
    fun fromApplicationStatus(value: String?) : ApplicationStatus? {
        return value?.let { ApplicationStatus.valueOf(it) }
    }

    @TypeConverter
    fun applicationStatusToString(applicationStatus: ApplicationStatus) : String? {
        return applicationStatus.toString()
    }

    @TypeConverter
    fun fromMarkerColor(value: String?) : MarkerColor? {
        return value?.let { MarkerColor.valueOf(it) }
    }

    @TypeConverter
    fun markerColorToString(markerColor: MarkerColor) : String? {
        return markerColor.toString()
    }

    @TypeConverter
    fun stringToMap(value: String) : HashMap<LocalDate, String> {
        val temp = Gson().fromJson(value, object: TypeToken<HashMap<Long, String>>() {}.type) as HashMap<Long, String>
        return temp.mapKeys { fromTimestampToLocalDate(it.key) } as HashMap<LocalDate, String>
    }

    @TypeConverter
    fun mapToString(value: Map<LocalDate, String>?): String {
        return if(value == null) "" else Gson().toJson(value.mapKeys { localDateToTimestamp(it.key) })
    }
}
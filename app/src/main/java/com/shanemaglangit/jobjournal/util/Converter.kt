package com.shanemaglangit.jobjournal.util

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.shanemaglangit.jobjournal.data.ApplicationStatus
import com.shanemaglangit.jobjournal.data.MarkerColor
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

    @TypeConverter
    fun fromMarkerColor(value: String?) : MarkerColor? {
        return value?.let { MarkerColor.valueOf(it) }
    }

    @TypeConverter
    fun markerColorToString(markerColor: MarkerColor) : String? {
        return markerColor.toString()
    }

    @TypeConverter
    fun stringToMap(value: String) : HashMap<Date, String> {
        val temp = Gson().fromJson(value, object: TypeToken<HashMap<Long, String>>() {}.type) as HashMap<Long, String>
        return temp.mapKeys { Date(it.key) } as HashMap<Date, String>
    }

    @TypeConverter
    fun mapToString(value: Map<Date, String>?): String {
        return if(value == null) "" else Gson().toJson(value.mapKeys { it.key.time })
    }
}
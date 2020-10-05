package com.shanemaglangit.jobjournal.util

import android.app.DatePickerDialog
import android.os.Build
import android.view.View
import com.shanemaglangit.jobjournal.data.JobApplication
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*

fun View.showDatePickerDialogOnClick(dateSetListener: DatePickerDialog.OnDateSetListener) {
    val calendar = Calendar.getInstance()
    val currentYear = calendar.get(Calendar.YEAR)
    val currentMonth = calendar.get(Calendar.MONTH)
    val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
    val datePickerDialog = DatePickerDialog(this.context, dateSetListener, currentYear, currentMonth, currentDay)
    this.setOnClickListener { datePickerDialog.show() }
}

fun LocalDate.formatToString() : String {
    val date = Date.from(this.atStartOfDay().toInstant(ZoneOffset.UTC))
    return SimpleDateFormat("MM.dd.yy").format(date)
}

fun LocalDate.formatToDateInstance() : String {
    val date = Date.from(this.atStartOfDay().toInstant(ZoneOffset.UTC))
    return SimpleDateFormat.getDateInstance().format(date)
}
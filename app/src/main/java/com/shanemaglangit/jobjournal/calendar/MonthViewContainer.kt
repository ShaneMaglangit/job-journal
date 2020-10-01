package com.shanemaglangit.jobjournal.calendar

import android.view.View
import com.kizitonwose.calendarview.ui.ViewContainer
import com.shanemaglangit.jobjournal.databinding.CalendarDayLayoutBinding
import com.shanemaglangit.jobjournal.databinding.CalendarMonthLayoutBinding

class MonthViewContainer(view: View) : ViewContainer(view) {
    val binding = CalendarMonthLayoutBinding.bind(view)
    val monthName = binding.calendarMonthText
}
package com.shanemaglangit.jobjournal.calendar

import android.view.View
import android.widget.TextView
import com.kizitonwose.calendarview.ui.ViewContainer
import com.shanemaglangit.jobjournal.R
import com.shanemaglangit.jobjournal.databinding.CalendarDayLayoutBinding
import kotlinx.android.synthetic.main.calendar_day_layout.view.*

class DayViewContainer(view: View) : ViewContainer(view) {
    val textView = CalendarDayLayoutBinding.bind(view).calendarDayText
}
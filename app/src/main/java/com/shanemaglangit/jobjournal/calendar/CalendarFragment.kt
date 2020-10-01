package com.shanemaglangit.jobjournal.calendar

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.shanemaglangit.jobjournal.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

@AndroidEntryPoint
class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater)

        // Binder for the layout container of the day
        binding.calendar.dayBinder = object : DayBinder<DayViewContainer> {
            // Method invoked when a new container is needed
            override fun create(view: View): DayViewContainer = DayViewContainer(view)

            // Method invoked when the containers are reused
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                container.textView.apply {
                    text = day.date.dayOfMonth.toString()
                    setTextColor(if (day.owner == DayOwner.THIS_MONTH) Color.DKGRAY else Color.LTGRAY)
                }
            }
        }

        // Binder for the layout container of the month
        binding.calendar.monthHeaderBinder = object : MonthHeaderFooterBinder<MonthViewContainer> {
            // Method invoked when a new container is needed
            override fun create(view: View): MonthViewContainer = MonthViewContainer(view)

            // Method invoked when the containers are reused
            override fun bind(container: MonthViewContainer, month: CalendarMonth) {
                container.monthName.text = month.yearMonth.month.name.toLowerCase().capitalize()
                container.year.text = month.year.toString()
            }
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()

        // Update the calendar to the current date
        val currentMonth = YearMonth.now()
        val firstMonth = currentMonth.minusYears(20)
        val lastMonth = currentMonth.plusYears(20)
        val firstDayOfWeek = WeekFields.of(Locale.getDefault()).firstDayOfWeek

        binding.calendar.setup(firstMonth, lastMonth, firstDayOfWeek)
        binding.calendar.scrollToMonth(currentMonth)
    }
}
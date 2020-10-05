package com.shanemaglangit.jobjournal.calendar

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.MonthScrollListener
import com.shanemaglangit.jobjournal.databinding.FragmentCalendarBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_calendar.*
import timber.log.Timber
import java.time.YearMonth
import java.time.temporal.WeekFields
import java.util.*

@AndroidEntryPoint
class CalendarFragment : Fragment() {
    private lateinit var binding: FragmentCalendarBinding
    private lateinit var applicationActionListAdapter: ApplicationActionListAdapter
    private val viewModel: CalendarViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(inflater)

        // Set up the recycler view components
        applicationActionListAdapter = ApplicationActionListAdapter()
        binding.recyclerApplicationActions.adapter = applicationActionListAdapter
        binding.recyclerApplicationActions.layoutManager = LinearLayoutManager(requireContext())

        // Binder for the layout container of the day
        binding.calendar.dayBinder = object : DayBinder<DayViewContainer> {
            // Method invoked when a new container is needed
            override fun create(view: View): DayViewContainer = DayViewContainer(view)

            // Method invoked when the containers are reused
            override fun bind(container: DayViewContainer, day: CalendarDay) {
                val actionCount = viewModel.getActionCount(day.date)
                container.textView.apply {
                    text = day.date.dayOfMonth.toString()
                    if(day.owner == DayOwner.THIS_MONTH) {
                        setTextColor(Color.DKGRAY)
                        if (actionCount != 0) {
                            background = ColorDrawable(
                                when {
                                    actionCount >= 24 -> Color.parseColor("#018786")
                                    actionCount >= 21 -> Color.parseColor("#019592")
                                    actionCount >= 18 -> Color.parseColor("#01A299")
                                    actionCount >= 15 -> Color.parseColor("#00B3A6")
                                    actionCount >= 12 -> Color.parseColor("#00C4B4")
                                    actionCount >= 9 -> Color.parseColor("#03DAC5")
                                    actionCount >= 6 -> Color.parseColor("#FFFFFF")
                                    actionCount >= 3 -> Color.parseColor("#70EFDE")
                                    else -> Color.parseColor("#C8FFF4")
                                }
                            )
                        }
                    } else {
                        setTextColor(Color.LTGRAY)
                    }
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

        binding.calendar.monthScrollListener = object: MonthScrollListener {
            override fun invoke(month: CalendarMonth) {
                val applicationActionList = viewModel.getApplicationActionsByMonth(month)
                applicationActionListAdapter.submitList(applicationActionList)
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

        // Refresh the calendar once the data are loaded
        viewModel.applicationActions.observe(viewLifecycleOwner, {
            binding.calendar.notifyCalendarChanged()
            binding.calendar.monthScrollListener?.invoke(binding.calendar.findFirstVisibleMonth()!!)
        })
    }
}
package com.shanemaglangit.jobjournal.util

import android.view.View
import android.widget.AdapterView
import android.widget.RadioGroup
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.shanemaglangit.jobjournal.R
import com.shanemaglangit.jobjournal.data.ApplicationStatus
import com.shanemaglangit.jobjournal.data.MarkerColor

@BindingAdapter("applicationStatus")
fun setApplicationStatus(view: AppCompatSpinner, value: ApplicationStatus) {
    if(value.ordinal != view.selectedItemPosition) view.setSelection(value.ordinal)
}

@InverseBindingAdapter(attribute = "applicationStatus", event="applicationStatusAttrChanged")
fun getApplicationStatus(view: AppCompatSpinner) : ApplicationStatus {
    return ApplicationStatus.valueOf(view.selectedItem.toString().toUpperCase())
}

@BindingAdapter("applicationStatusAttrChanged")
fun setApplicationStatusListener(view: AppCompatSpinner, attrChange: InverseBindingListener) {
    view.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            attrChange.onChange()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }
}

@BindingAdapter("selectedMarkerColor")
fun setSelectedMarkerColor(view: RadioGroup, value: MarkerColor) {
    val selectedButton = when(value) {
        MarkerColor.PINK -> R.id.radio_pink
        MarkerColor.RED -> R.id.radio_red
        MarkerColor.ORANGE -> R.id.radio_orange
        MarkerColor.YELLOW -> R.id.radio_yellow
        MarkerColor.GREEN -> R.id.radio_green
        MarkerColor.BLUE -> R.id.radio_blue
        MarkerColor.VIOLET -> R.id.radio_violet
    }

    if(selectedButton != view.checkedRadioButtonId) view.check(selectedButton)
}

@InverseBindingAdapter(attribute = "selectedMarkerColor", event = "selectedMarkerColorAttrChanged")
fun getSelectedMarkerColor(view: RadioGroup) : MarkerColor {
    return when(view.checkedRadioButtonId) {
        R.id.radio_red -> MarkerColor.RED
        R.id.radio_orange -> MarkerColor.ORANGE
        R.id.radio_yellow -> MarkerColor.YELLOW
        R.id.radio_green -> MarkerColor.GREEN
        R.id.radio_blue -> MarkerColor.BLUE
        R.id.radio_violet -> MarkerColor.VIOLET
        else -> MarkerColor.PINK
    }
}

@BindingAdapter("selectedMarkerColorAttrChanged")
fun setSelectedMarkerColorListener(view: RadioGroup, attrChange: InverseBindingListener) {
    view.setOnCheckedChangeListener { _, _ ->  attrChange.onChange() }
}
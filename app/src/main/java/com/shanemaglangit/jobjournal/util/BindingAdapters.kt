package com.shanemaglangit.jobjournal.util

import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import com.shanemaglangit.jobjournal.data.ApplicationStatus

@BindingAdapter("applicationStatus")
fun setApplicationStatus(view: AppCompatSpinner, value: ApplicationStatus) {
    if(value.ordinal != view.selectedItemPosition) view.setSelection(value.ordinal)
}

@InverseBindingAdapter(attribute = "applicationStatus", event="applicationStatusAttrChanged")
fun getApplicationStatus(view: AppCompatSpinner) : ApplicationStatus {
    return ApplicationStatus.valueOf(view.selectedItem.toString().toUpperCase())
}

@BindingAdapter("app:applicationStatusAttrChanged")
fun setApplicationStatusListener(view: AppCompatSpinner, attrChange: InverseBindingListener) {
    view.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            attrChange.onChange()
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {}
    }
}
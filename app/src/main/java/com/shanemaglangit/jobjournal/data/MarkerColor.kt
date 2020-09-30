package com.shanemaglangit.jobjournal.data

import android.content.Context
import androidx.core.content.ContextCompat
import com.shanemaglangit.jobjournal.R

enum class MarkerColor {
    PINK, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET;

    companion object {
        fun getColor(context: Context, markerColor: MarkerColor): Int {
            val colorId = when (markerColor) {
                PINK -> R.color.pastelPink
                RED -> R.color.pastelRed
                ORANGE -> R.color.pastelOrange
                YELLOW -> R.color.pastelYellow
                GREEN -> R.color.pastelGreen
                BLUE -> R.color.pastelBlue
                VIOLET -> R.color.pastelViolet
            }

            return ContextCompat.getColor(context, colorId)
        }
    }
}
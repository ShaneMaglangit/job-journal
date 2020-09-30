package com.shanemaglangit.jobjournal.data

import android.content.Context
import androidx.core.content.ContextCompat

enum class MarkerColor {
    PINK, RED, ORANGE, YELLOW, GREEN, BLUE, VIOLET;

    companion object {
        fun getColor(context: Context, markerColor: MarkerColor): Int {
            val name = "pastel${markerColor.name.toLowerCase().capitalize()}"
            val colorId = context.resources.getIdentifier(name, "color", context.packageName)
            return ContextCompat.getColor(context, colorId)
        }
    }
}
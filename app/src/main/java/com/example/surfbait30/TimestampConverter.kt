package com.example.surfbait30

import java.text.SimpleDateFormat
import java.util.*

object TimestampConverter {
    // Helper method that takes the epoch timestamp provided in
    // the MSW json posts and converts it to a time format
    fun getTimeFromSeconds(ts: Int): String {
        val sdf = SimpleDateFormat("ha", Locale.US) // Time format: 12AM
        val date = Date(ts * 1000L)
        return sdf.format(date).toString()
    }

    // Returns a string of today's date in the format of Monday, January 31
    fun getTodayDate(): String {
        return SimpleDateFormat("EEEE, MMMM d", Locale.US).format(Date())
    }
}
package com.miftakhularzak.footballclubapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateAndTimeFormatter {

    @SuppressLint("SimpleDateFormat")
    fun toSimpleStringDate(date: String?):String? = with(date ?: String){
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)
        SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH).format(date)
    }
    fun toSimpleStringTime(time: String?):String? = with(time ?:String){
        val oldTimeFormat = SimpleDateFormat("HH:mm:ss", Locale.ENGLISH)
        oldTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
        val timeEvent = oldTimeFormat.parse(time)
         SimpleDateFormat("HH:mm", Locale.getDefault()).format(timeEvent)
    }

}
package com.miftakhularzak.footballclubapp.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.*

class DateAndTimeFormatter {

    @SuppressLint("SimpleDateFormat")
    fun toSimpleStringDate(date: String?):String? = with(date ?: String){
        if(date!=null) {
            val oldDateFormat: Date? = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(date)
            SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH).format(oldDateFormat)
        }
        else {
            return "Date is Unknown"
        }
    }
    fun toSimpleStringTime(time: String?):String? = with(time ?:String){
        if(time!=null){
        val oldTimeFormat: SimpleDateFormat? = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        oldTimeFormat?.timeZone = TimeZone.getTimeZone("UTC")
        val timeEvent = oldTimeFormat?.parse(time)
         SimpleDateFormat("HH:mm", Locale.getDefault()).format(timeEvent)
        }else{
            return "Time is Unknown"
        }
    }

}
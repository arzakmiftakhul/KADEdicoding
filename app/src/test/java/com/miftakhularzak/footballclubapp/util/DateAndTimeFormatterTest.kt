package com.miftakhularzak.footballclubapp.util

import org.junit.Assert.assertEquals
import org.junit.Test

class DateAndTimeFormatterTest {
    
    @Test
    fun toSimpleStringDate() {
        val eventDate : String = "2018-10-07"
        assertEquals("Sun, 07 Oct 2018", DateAndTimeFormatter().toSimpleStringDate(eventDate))
    }

    @Test
    fun toSimpleStringTime() {
        val strTime : String = "14:00:00+00:00"
        assertEquals("21:00", DateAndTimeFormatter().toSimpleStringTime(strTime))
    }
}
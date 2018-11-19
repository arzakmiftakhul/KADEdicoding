package com.miftakhularzak.footballclubapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.miftakhularzak.footballclubapp.R.id.*
import com.miftakhularzak.footballclubapp.activity.MainActivity
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class MainActivityTest {
    @Rule
    @JvmField var fragmentRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun A_testRecyclerViewBehaviour(){
        Thread.sleep(3000)
        //mengecek Recyclerview list_next_match
        //melakukan tindakan scroll sampai item ke 10
        //melakukan tindakan klik pada item 10
        //menekan tombol kembali
        //melakukan swift ke kiri
        onView(withId(list_next_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10,click()))
        pressBack()
        onView(withId(list_next_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, swipeLeft()))
        Thread.sleep(1000)
        //mengcek recyclerview list_last_match
        //melakukan tindakan scroll ke item ke 10
        //melakukan tindakan klik item ke 10
        //menekan tombol kembali
        onView(withId(list_last_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        pressBack()
        //mengecek tombol tem dan melakukan klik
        onView(withId(team_button)).
                check(matches(isDisplayed())).
                perform(click())
    }
    @Test
    fun B_testAddNextAndPrevMatchToFavorite(){
        //Menunggu 3 sekon
        //mengecek recyclerview list_next_match apakah ada
        //melakukan tindakan scroll sampai item ke 12
        //melakukan tindakan klik item ke 12
        Thread.sleep(3000)
        onView(withId(list_next_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(12,click()))
        //meunggu 2 sekon
        //mengecek apaka ada tombol favorite
        //melakukan tindakak klik pada tombol favorite
        //menunggu 1 sekon
        //menekan tombol kembali
        Thread.sleep(2000)
        onView(withId(add_to_favorite)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(1000)
        pressBack()
        //melakukan swipe ke kiri pada item ke 5 pada list_next_match
        //mengecek apakah ada recyclerview list_last_match
        //melakukan scroll sampai posisi 10
        //melakukan tindakan klik pada item ke 10
        onView(withId(list_next_match)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, swipeLeft()))
        onView(withId(list_last_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        //menunggu 2 sekon
        //mengecek apakah terdapat tombol favorite
        //melakukan tindakan klik pada tombol favorite
        //menunggu 1 sekon
        //menekan tombol kembali
        Thread.sleep(2000)
        onView(withId(add_to_favorite)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(1000)
        pressBack()
        //mengecek apakah terdapat tombol nav button favorite
        //melakukan tindakan klik pada nav button favorite
        //menunggu 1 sekon
        onView(withId(fav_button)).perform(click())
        Thread.sleep(1000)
        //mengecek apakah ada recyclerview list_fav_match
        //melakukan tindakan klik pada item pertama
        //menunggu 2 sekon
        //menekan tombol kembali
        onView(withId(list_fav_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Thread.sleep(2000)
        pressBack()
    }
    @Test
    fun C_testRemoveFromFavorite(){
        //menunggu 2 sekon
        //mengecek apakah terdapat tombol nav button favorite
        //melakukan tindakan klik pada tombol nav favorite
        //menunggu 1 sekon
        Thread.sleep(2000)
        onView(withId(fav_button)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(1000)
        //mengecek apakah terdapat recyclerview list_fav_match
        //melakukan tindakan klik pada item pertama
        //menunggu 2 sekon
        onView(withId(list_fav_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(2000)
        //mengecek apakah terdapat tombol favorite
        //melakukan tindakan klik pada tombol favorite
        //menunggu 1 sekon
        //menekan tombol kembali
        onView(withId(add_to_favorite)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(1000)
        pressBack()
        //mengecek apakah terdapat tampilan swipe_refresh
        //melakukan tindakan swipe ke bawah
        onView(withId(swipe_refresh)).
                check(matches(isDisplayed())).
                perform(swipeDown())
        Thread.sleep(2000)
    }

}
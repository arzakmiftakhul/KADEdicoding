package com.miftakhularzak.footballclubapp

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.swipeDown
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.miftakhularzak.footballclubapp.R.id.*
import com.miftakhularzak.footballclubapp.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Rule
    @JvmField var fragmentRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testRecyclerViewBehaviour(){
        Thread.sleep(2000)
        onView(withId(list_next_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10,click()))
        pressBack()
        onView(withId(prev_match_bt)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(2000)
        onView(withId(list_last_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        pressBack()
        onView(withId(fav_match_bt)).
                check(matches(isDisplayed())).
                perform(click())
    }
    @Test
    fun testAddNextMatchToFavorite(){
        Thread.sleep(2000)
        onView(withId(next_match_bt)).perform(click())
        Thread.sleep(2000)
        onView(withId(list_next_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(12,click()))
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(1000)
        pressBack()
        onView(withId(list_next_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        Thread.sleep(2000)
        onView(withId(add_to_favorite)).
                check(matches(isDisplayed())).
                perform(click())
        pressBack()
        onView(withId(fav_match_bt)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(1000)
        onView(withId(list_fav_match)).
                check(matches(isDisplayed()))
    }
    @Test
    fun testAddPrevMatchToFavorite(){
        Thread.sleep(2000)
        onView(withId(prev_match_bt)).perform(click())
        Thread.sleep(2000)
        onView(withId(list_last_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(12)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(12,click()))
        Thread.sleep(3000)
        onView(withId(add_to_favorite)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(1000)
        pressBack()
        onView(withId(list_last_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(9)).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(9, click()))
        Thread.sleep(2000)
        onView(withId(add_to_favorite)).
                check(matches(isDisplayed())).
                perform(click())
        pressBack()
        onView(withId(fav_match_bt)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(1000)
        onView(withId(list_fav_match)).
                check(matches(isDisplayed()))
    }
    @Test
    fun testRemoveFromFavorite(){
        Thread.sleep(2000)
        onView(withId(fav_match_bt)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(1000)
        onView(withId(list_fav_match)).
                check(matches(isDisplayed())).
                perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(1, click()))
        Thread.sleep(2000)
        onView(withId(add_to_favorite)).
                check(matches(isDisplayed())).
                perform(click())
        Thread.sleep(1000)
        pressBack()
        onView(withId(swipe_refresh)).
                check(matches(isDisplayed())).
                perform(swipeDown())
    }

}
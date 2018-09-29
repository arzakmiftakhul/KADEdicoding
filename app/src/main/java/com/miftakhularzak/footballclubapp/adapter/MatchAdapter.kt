package com.miftakhularzak.footballclubapp.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.miftakhularzak.footballclubapp.fragments.NextMatchFragment
import com.miftakhularzak.footballclubapp.fragments.PrevMatchFragment

class MatchAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? = when (position) {
        0 -> NextMatchFragment.newInstance()
        1 -> PrevMatchFragment.newInstance()
        else -> null
    }

    override fun getPageTitle(position: Int): CharSequence = when (position) {
        0 -> "Next Match"
        1 -> "Previous Match"
        else -> ""
    }

    override fun getCount(): Int = 2
}
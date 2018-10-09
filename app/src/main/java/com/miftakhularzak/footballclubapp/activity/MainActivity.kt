package com.miftakhularzak.footballclubapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.R.id.*
import com.miftakhularzak.footballclubapp.fragments.FavoriteMatchFragment
import com.miftakhularzak.footballclubapp.fragments.MatchFragment
import com.miftakhularzak.footballclubapp.fragments.NextMatchFragment
import com.miftakhularzak.footballclubapp.fragments.PrevMatchFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                next_match_bt -> {
                    loadNextMatchFragment(savedInstanceState)
                   // loadMatchFragment(savedInstanceState)
                }
                prev_match_bt -> {
                    loadPrevMatchFragment(savedInstanceState)

                }
                fav_match_bt ->{
                    loadFavMatchFragment(savedInstanceState)

                }
            }
            true
        }
        bottom_navigation.selectedItemId = next_match_bt
    }
    private fun loadNextMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, NextMatchFragment(), NextMatchFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadPrevMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, PrevMatchFragment(), PrevMatchFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadFavMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteMatchFragment(), FavoriteMatchFragment::class.java.simpleName)
                    .commit()
        }
    }
    private fun loadMatchFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, MatchFragment(), MatchFragment::class.java.simpleName)
                    .commit()
        }
    }
}









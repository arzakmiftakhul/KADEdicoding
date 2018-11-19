package com.miftakhularzak.footballclubapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.R.id.*
import com.miftakhularzak.footballclubapp.fragments.FavoriteFragment
import com.miftakhularzak.footballclubapp.fragments.MatchFragment
import com.miftakhularzak.footballclubapp.fragments.TeamFragment
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                match_button -> {
                    loadMatchFragment(savedInstanceState)

                }
                team_button -> {
                    loadTeamFragment(savedInstanceState)
                }
                fav_button -> {
                    loadFavoriteFragment(savedInstanceState)

                }
            }
            true
        }
        bottom_navigation.selectedItemId = match_button
    }

    private fun loadTeamFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamFragment(), TeamFragment::class.java.simpleName)
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

    private fun loadFavoriteFragment(savedInstanceState: Bundle?) {
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteFragment(), FavoriteFragment::class.java.simpleName)
                    .commit()
        }
    }

}









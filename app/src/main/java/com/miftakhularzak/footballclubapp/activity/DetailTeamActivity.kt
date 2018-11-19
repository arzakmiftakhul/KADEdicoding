package com.miftakhularzak.footballclubapp.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.fragments.TeamOverviewFragment
import com.miftakhularzak.footballclubapp.fragments.TeamPlayerFragment
import com.miftakhularzak.footballclubapp.helper.databaseteam
import com.miftakhularzak.footballclubapp.model.FavoriteTeam
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar


class DetailTeamActivity : AppCompatActivity() {

    private lateinit var teamLogo: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private var teamId: String = ""
    private var teamLeague: String = ""

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val intent = intent

        teamLogo = team_logo
        teamName = team_name
        teamFormedYear = team_formed_year
        teamStadium = team_stadium

        Picasso.get().load(intent.getStringExtra("logo")).into(teamLogo)

        teamName.text = intent.getStringExtra("name")
        teamFormedYear.text = intent.getStringExtra("formedyear")
        teamStadium.text = intent.getStringExtra("stadium")
        teamId = intent.getStringExtra("id")
        id = intent.getStringExtra("id")
        teamLeague = intent.getStringExtra("league")
        favoriteState()
        val matchPagerAdapter = MatchPagerAdapter(supportFragmentManager)
        matchPagerAdapter.addFrag(TeamOverviewFragment(), "Overview")
        matchPagerAdapter.addFrag(TeamPlayerFragment(), "Player")
        htab_viewpager.adapter = matchPagerAdapter
        htab_tabs.setupWithViewPager(htab_viewpager)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item?.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavorite) removeFromFavorite()
                else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavorite() {
        try {
            databaseteam.use {
                insert(FavoriteTeam.TABLE_FAVORITE_TEAM,
                        FavoriteTeam.TEAM_ID to teamId,
                        FavoriteTeam.TEAM_NAME to teamName.text,
                        FavoriteTeam.TEAM_BADGE to intent.getStringExtra("logo"),
                        FavoriteTeam.TEAM_OVERVIEW to intent.getStringExtra("overview"),
                        FavoriteTeam.TEAM_FORMED_YEAR to teamFormedYear.text,
                        FavoriteTeam.TEAM_STADIUM to teamStadium.text,
                        FavoriteTeam.TEAM_LEAGUE to teamLeague)
            }
            snackbar(detail_team_container, "Added to favorite").show()
//            toast("added to favorite")
        } catch (e: SQLiteConstraintException) {
//            toast("gagal")

        }
    }

    private fun removeFromFavorite() {
        try {
            databaseteam.use {
                delete(FavoriteTeam.TABLE_FAVORITE_TEAM, "(TEAM_ID = {id})", "id" to id)
            }
            snackbar(detail_team_container, "Removed from favorite").show()
//            toast("removed")
        } catch (e: SQLiteConstraintException) {
            snackbar(detail_team_container, e.localizedMessage).show()
//            toast("gagal remove")
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorite)
    }

    private fun favoriteState() {
        databaseteam.use {
            val result = select(FavoriteTeam.TABLE_FAVORITE_TEAM).whereArgs("(TEAM_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<FavoriteTeam>())
            if (!favorite.isEmpty()) isFavorite = true
        }
    }

    internal inner class MatchPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun getItem(position: Int): Fragment {
            return mFragmentList[position]
        }

        override fun getCount(): Int {
            return mFragmentList.size
        }

        fun addFrag(fragment: Fragment, title: String) {
            mFragmentList.add(fragment)
            mFragmentTitleList.add(title)
        }

        override fun getPageTitle(position: Int): CharSequence {
            return mFragmentTitleList[position]
        }
    }
}



package com.miftakhularzak.footballclubapp.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.model.MainPresenter
import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.model.Team
import com.miftakhularzak.footballclubapp.util.MainView
import com.miftakhularzak.footballclubapp.util.invisible
import com.miftakhularzak.footballclubapp.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import java.text.SimpleDateFormat
import java.util.*

class DetailMatchActivity : AppCompatActivity(), MainView {

    lateinit var date: TextView
    lateinit var time: TextView
    lateinit var homeBadge: ImageView
    lateinit var awayBadge: ImageView
    lateinit var homeTeam: TextView
    lateinit var awayTeam: TextView
    lateinit var homeScore: TextView
    lateinit var awayScore: TextView
    lateinit var homeShots: TextView
    lateinit var awayShots: TextView
    lateinit var homeGoalDetails: TextView
    lateinit var awayGoalDetails: TextView
    lateinit var homeYellowCards: TextView
    lateinit var awayYellowCards: TextView
    lateinit var homeRedCards: TextView
    lateinit var awayRedCards: TextView
    lateinit var homeGoalkeeper: TextView
    lateinit var awayGoalkeeper: TextView
    lateinit var homeDefense: TextView
    lateinit var awayDefense: TextView
    lateinit var homeForward: TextView
    lateinit var awayForward: TextView
    lateinit var homeMidfield: TextView
    lateinit var awayMidfield: TextView
    lateinit var homeSubtitutes: TextView
    lateinit var awaySubstitutes: TextView

    private lateinit var presenter: MainPresenter
    lateinit var progressBar: ProgressBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        date = date_event
        time = time_event
        homeBadge = home_badge
        awayBadge = away_badge
        homeTeam = home_team
        awayTeam = away_team
        homeScore = home_score
        awayScore = away_score
        homeShots = home_shots
        awayShots = away_shots
        homeGoalDetails = home_goal_details
        awayGoalDetails = away_goal_details
        homeYellowCards = home_yellow_card
        awayYellowCards = away_yellow_card
        homeRedCards = home_red_card
        awayRedCards = away_red_card
        homeGoalkeeper = home_goalkeeper
        awayGoalkeeper = away_goalkeeper
        homeDefense = home_defense
        awayDefense = away_defense
        homeForward = home_forward
        awayForward = away_forward
        homeMidfield = home_midfield
        awayMidfield = away_midfield
        homeSubtitutes = home_substitutes
        awaySubstitutes = away_substitutes

        progressBar = progress_detail as ProgressBar
        val request = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, request, gson)

        //presenter.getTeamList("134778")


        val bundle = intent.getBundleExtra("match_detail_bundle")
        val events = bundle.getParcelable("match_detail") as Match

        val oldDateFormat = SimpleDateFormat("yyyy-MM-dd").parse(events.dateEvent)
        val newDateFormat = SimpleDateFormat("E , dd MMM yyyy").format(oldDateFormat)

        val oldTimeFormat = SimpleDateFormat("HH:mm:ss")
        oldTimeFormat.timeZone = TimeZone.getTimeZone("UTC")
        val timeEvent = oldTimeFormat.parse(events.timeEvent)
        val newTimeFormat = SimpleDateFormat("HH:mm", Locale.getDefault()).format(timeEvent)

        date.text = newDateFormat
        time.text = newTimeFormat
        homeTeam.text = events.homeTeam
        awayTeam.text = events.awayTeam
        homeScore.text = events.homeScore
        awayScore.text = events.awayScore
        homeShots.text = events.homeShots
        awayShots.text = events.awayShots
        homeGoalDetails.text = events.homeGoalDetails
        awayGoalDetails.text = events.awayGoalDetails
        homeYellowCards.text = events.homeYellowCards
        awayYellowCards.text = events.awayYellowCards
        homeRedCards.text = events.homeRedCards
        awayRedCards.text = events.awayRedCards
        homeGoalkeeper.text = events.homeLineupGoalkeeper
        awayGoalkeeper.text = events.awayLineupGoalkeeper
        homeDefense.text = events.homeLineupDefense
        awayDefense.text = events.awayLineupDefense
        homeMidfield.text = events.homeLineupMidfield
        awayMidfield.text = events.awayLineupMidfield
        homeForward.text = events.homeLineupForward
        awayForward.text = events.awayLineupForward
        homeSubtitutes.text = events.homeLineupSubstitutes
        awaySubstitutes.text = events.awayLineupSubstitutes

        homeForward.text = events.homeLineupForward
        awayForward.text = events.awayLineupForward


        presenter.getTeamList(events.idHome, events.idAway)
        Log.d("PARCEL", "parcel" + events.homeTeam + " vs " + events.awayTeam)

    }
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    override fun showEventList(data: List<Match>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun showTeamHome(data: List<Team>, data2: List<Team>) {
        Picasso.get().load(data.get(0).teamBadge).into(homeBadge)
        Picasso.get().load(data2.get(0).teamBadge).into(awayBadge)

    }
}

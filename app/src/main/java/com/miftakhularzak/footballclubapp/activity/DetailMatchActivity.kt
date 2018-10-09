package com.miftakhularzak.footballclubapp.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.R
import com.miftakhularzak.footballclubapp.R.drawable.ic_add_to_favorite
import com.miftakhularzak.footballclubapp.R.drawable.ic_added_to_favorite
import com.miftakhularzak.footballclubapp.R.id.add_to_favorite
import com.miftakhularzak.footballclubapp.R.menu.detail_menu
import com.miftakhularzak.footballclubapp.`interface`.DetailView
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.helper.database
import com.miftakhularzak.footballclubapp.model.Favorite
import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.model.Team
import com.miftakhularzak.footballclubapp.presenter.DetailPresenter
import com.miftakhularzak.footballclubapp.util.DateAndTimeFormatter
import com.miftakhularzak.footballclubapp.util.invisible
import com.miftakhularzak.footballclubapp.util.visible
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_match.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar

class DetailMatchActivity : AppCompatActivity(), DetailView {


    lateinit var dateEvent: TextView
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

    private lateinit var presenter: DetailPresenter
    lateinit var progressBar: ProgressBar
    lateinit var homeId : String
    lateinit var awayId : String
    lateinit var eventId : String

    private var menuItem: Menu? = null
    private var isFavorite: Boolean = false
    private lateinit var id : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)
        val intent = intent
        id = intent.getStringExtra("id")
        supportActionBar?.title = "Match Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        dateEvent = date_event
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

        presenter = DetailPresenter(this,request,gson)


         eventId = intent.getStringExtra("eventid")
         homeId = intent.getStringExtra("homeid")
         awayId = intent.getStringExtra("awayid")

        favoriteState()

        presenter.getDetailMatch(eventId,homeId,awayId)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(detail_menu,menu)
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
            add_to_favorite -> {
                if (isFavorite) removeFromFavorite()
                else addToFavorite()

                isFavorite = !isFavorite
                setFavorite()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
    private fun addToFavorite(){
        try {
            database.use {
                insert(Favorite.TABLE_FAVORITE,
                        Favorite.EVENT_ID to eventId,
                        Favorite.HOME_ID to homeId,
                        Favorite.AWAY_ID to awayId,
                        Favorite.HOME_TEAM to homeTeam.text,
                        Favorite.AWAY_TEAM to awayTeam.text,
                        Favorite.HOME_SCORE to homeScore.text,
                        Favorite.AWAY_SCORE to awayScore.text,
                        Favorite.DATE_EVENT to dateEvent.text,
                        Favorite.TIME_EVENT to time.text)
            }
            snackbar(RL, "Added to favorite").show()
        } catch (e: SQLiteConstraintException){
            snackbar(RL, e.localizedMessage).show()

        }
    }

    private fun removeFromFavorite(){
        try{
            database.use {
                delete(Favorite.TABLE_FAVORITE,"(EVENT_ID = {id})","id" to id)
            }
            snackbar(RL, "Removed from favorite").show()
        }catch (e:SQLiteConstraintException){
            snackbar(RL, e.localizedMessage).show()
        }
    }
    private fun setFavorite(){
        if(isFavorite)
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_added_to_favorite)
        else
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, ic_add_to_favorite)
    }
    private fun favoriteState(){
        database.use {
            val result = select(Favorite.TABLE_FAVORITE).whereArgs("(EVENT_ID = {id})",
                    "id" to id)
            val favorite = result.parseList(classParser<Favorite>())
            if(!favorite.isEmpty()) isFavorite = true
        }
    }



    override fun hideLoading() {
        progressBar.invisible()
    }
    override fun showDetailMatch(data1: List<Match>, data2: List<Team>, data3: List<Team>) {
        dateEvent.text = DateAndTimeFormatter().toSimpleStringDate(data1[0].dateEvent)
        time.text = DateAndTimeFormatter().toSimpleStringTime(data1[0].timeEvent)
        homeTeam.text = data2[0].teamName
        awayTeam.text = data3[0].teamName
        Picasso.get().load(data2[0].teamBadge).into(homeBadge)
        Picasso.get().load(data3[0].teamBadge).into(awayBadge)
        homeScore.text = data1[0].homeScore
        awayScore.text = data1[0].awayScore
        homeShots.text = data1[0].homeShots
        awayShots.text = data1[0].awayShots
        homeGoalDetails.text = data1[0].homeGoalDetails
        awayGoalDetails.text = data1[0].awayGoalDetails
        homeYellowCards.text = data1[0].homeYellowCards
        awayYellowCards.text = data1[0].awayYellowCards
        homeRedCards.text = data1[0].homeRedCards
        awayRedCards.text = data1[0].awayRedCards
        homeGoalkeeper.text = data1[0].homeLineupGoalkeeper
        awayGoalkeeper.text = data1[0].awayLineupGoalkeeper
        homeDefense.text = data1[0].homeLineupDefense
        awayDefense.text = data1[0].awayLineupDefense
        homeForward.text = data1[0].homeLineupForward
        awayForward.text = data1[0].awayLineupForward
        homeSubtitutes.text = data1[0].homeLineupSubstitutes
        awaySubstitutes.text = data1[0].awayLineupSubstitutes
        homeMidfield.text = data1[0].homeLineupMidfield
        awayMidfield.text = data1[0].awayLineupMidfield

    }

    override fun showLoading() {
        progressBar.visible()
    }

}

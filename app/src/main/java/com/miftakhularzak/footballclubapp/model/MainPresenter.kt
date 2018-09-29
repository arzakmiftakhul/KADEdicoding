package com.miftakhularzak.footballclubapp.model

import android.util.Log
import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.R.array.league
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.api.TheSportDBApi
import com.miftakhularzak.footballclubapp.util.MainView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(private val view: MainView,
                    private val apiRepository: ApiRepository,
                    private val gson: Gson) {
    fun getEventsList(leagueId: String?, events: String?) {
        view.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getNextMatch(leagueId, events)), MatchResponse::class.java


            )
            Log.d("DATA EVENT", "DOASYNC" + data)
            uiThread {
                view.hideLoading()
                view.showEventList(data.events)
            }
        }

    }

    fun getTeamList(homeTeam: String?, awayTeam: String?) {
        view.showLoading()
        Log.d("CEKDATA", "Show loading for" + league)

        doAsync {


            val data = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(homeTeam)), TeamResponse::class.java
            )
            val data2 = gson.fromJson(apiRepository
                    .doRequest(TheSportDBApi.getTeams(awayTeam)), TeamResponse::class.java
            )

            uiThread {
                view.hideLoading()
                view.showTeamHome(data.teams, data2.teams)
                Log.d("CEKDATA", "UIThread" + view.showTeamHome(data.teams, data2.teams).toString())

            }
        }

    }
}
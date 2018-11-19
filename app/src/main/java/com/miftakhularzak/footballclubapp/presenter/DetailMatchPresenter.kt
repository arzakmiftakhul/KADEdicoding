package com.miftakhularzak.footballclubapp.presenter

import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.`interface`.DetailView
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.api.TheSportDBApi
import com.miftakhularzak.footballclubapp.model.MatchResponse
import com.miftakhularzak.footballclubapp.model.TeamResponse
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class DetailMatchPresenter(private val view : DetailView,
                           private val apiRepository: ApiRepository,
                           private val gson: Gson,
                           private val context : CoroutineContextProvider
                      = CoroutineContextProvider()) {
    fun getDetailMatch(eventId : String?, homeId : String?, awayId : String?){
        view.showLoading()

        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository.
                    doRequest(TheSportDBApi.getMatchDetail(eventId)), MatchResponse::class.java)
            }
            val data2 = bg{
                gson.fromJson(apiRepository.
                        doRequest(TheSportDBApi.getTeams(homeId)), TeamResponse::class.java)
            }
            val data3 = bg{
                gson.fromJson(apiRepository.
                        doRequest(TheSportDBApi.getTeams(awayId)), TeamResponse::class.java)
            }
            view.showDetailMatch(data.await().events, data2.await().teams,data3.await().teams)
            view.hideLoading()
        }
    }

}
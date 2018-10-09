package com.miftakhularzak.footballclubapp.presenter

import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.`interface`.MatchView
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.api.TheSportDBApi
import com.miftakhularzak.footballclubapp.model.MatchResponse
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class MatchPresenter(private val view : MatchView,
                     private val apiRepository : ApiRepository,
                     private val gson : Gson,
                     private val context : CoroutineContextProvider
                                = CoroutineContextProvider()) {
    fun getMatchList(leagueId : String?, events : String?){
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.
                    doRequest(TheSportDBApi.getMatch(leagueId, events)),
                        MatchResponse::class.java)
//            Log.d("loading","loading")
            }
            view.showMatchList(data.await().events)
            view.hideLoading()
        }
    }

}
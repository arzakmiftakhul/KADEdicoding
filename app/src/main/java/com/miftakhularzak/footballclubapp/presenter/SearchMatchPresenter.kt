package com.miftakhularzak.footballclubapp.presenter

import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.`interface`.MatchView
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.api.TheSportDBApi
import com.miftakhularzak.footballclubapp.model.MatchSearchResponse
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class SearchMatchPresenter(private val view : MatchView,
                           private val apiRepository : ApiRepository,
                           private val gson : Gson,
                           private val context : CoroutineContextProvider
                      = CoroutineContextProvider()) {
    fun searchMatchList(matchName: String?){
        view.showLoading()
        async(context.main){
            val data = bg {
                gson.fromJson(apiRepository.
                        doRequest(TheSportDBApi.searchMatch(matchName)),
                        MatchSearchResponse::class.java)

//            Log.d("loading","loading")
            }
//            Log.d("SEARCH",TheSportDBApi.searchMatch(matchName))
//            Log.d("SEARCH",data.await().event[1].eventName)
            view.showMatchList(data.await().event)
            view.hideLoading()
        }
    }

}
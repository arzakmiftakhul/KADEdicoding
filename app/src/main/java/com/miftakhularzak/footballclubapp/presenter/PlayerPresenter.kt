package com.miftakhularzak.footballclubapp.presenter

import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.`interface`.PlayerView
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.api.TheSportDBApi
import com.miftakhularzak.footballclubapp.model.PlayerResponse
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PlayerPresenter(private val view: PlayerView,
                      private val apiRepository: ApiRepository,
                      private val gson : Gson,
                      private val context : CoroutineContextProvider
                      = CoroutineContextProvider()) {
    fun getPlayerList(teamId: String?) {
        view.showLoading()

        async(context.main) {
            val data = bg {
                gson.fromJson(apiRepository.doRequest(TheSportDBApi.getPlayerList(teamId)), PlayerResponse::class.java)
            }
//            Log.d("DATARESPONSE", "CEK" + data.await().player)

            view.showPlayerList(data.await().player)
            view.hideLoading()
        }

    }
}
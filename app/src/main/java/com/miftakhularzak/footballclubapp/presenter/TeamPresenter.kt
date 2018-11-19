package com.miftakhularzak.footballclubapp.presenter

import android.util.Log
import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.`interface`.TeamView
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.api.TheSportDBApi
import com.miftakhularzak.footballclubapp.model.TeamResponse
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class TeamPresenter(private val view: TeamView,
                    private val apiRepository: ApiRepository,
                    private val gson : Gson,
                    private val context : CoroutineContextProvider
                    = CoroutineContextProvider()) {
    fun getTeamList(leagueName: String?){
        view.showLoading()
        Log.d("DATARESPONSE", "CEK "+leagueName)

        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository.
                        doRequest(TheSportDBApi.getTeamList(leagueName)), TeamResponse::class.java)
            }
            Log.d("DATARESPONSE", "CEK"+data.await().teams)

            view.showTeamList(data.await().teams)
            view.hideLoading()
        }

    }
    fun searchTeamList(teamName: String?){
        view.showLoading()
//        Log.d("DATARESPONSE", "CEK "+teamName)

        async(context.main){
            val data = bg{
                gson.fromJson(apiRepository.
                        doRequest(TheSportDBApi.searchTeam(teamName)), TeamResponse::class.java)
            }
//            Log.d("DATARESPONSE", "CEK"+TheSportDBApi.searchTeam(teamName))
//            Log.d("DATARESPONSE", "CEK"+data.await().teams)

            view.showTeamList(data.await().teams)
            view.hideLoading()
        }

    }

//    fun getTeamDetail(teamId: String?){
//        view.showLoading()
//
//        async(context.main){
//            val data= bg {
//                gson.fromJson(apiRepository.
//                        doRequest(TheSportDBApi.getTeamList(teamId)), TeamResponse::class.java)
//            }
//            view.showTeamList(data.await().teams)
//            view.hideLoading()
//        }
//    }
}
package com.miftakhularzak.footballclubapp.util

import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.model.Team

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showTeamHome(data: List<Team>,data2:List<Team>)
    fun showEventList(data:List<Match>)
}
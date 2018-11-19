package com.miftakhularzak.footballclubapp.`interface`

import com.miftakhularzak.footballclubapp.model.Team

interface TeamView {
    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<Team>)
}
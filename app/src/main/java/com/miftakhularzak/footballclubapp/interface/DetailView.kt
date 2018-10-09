package com.miftakhularzak.footballclubapp.`interface`

import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.model.Team

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data1 : List<Match>, data2 : List<Team>, data3 : List<Team>)
}
package com.miftakhularzak.footballclubapp.`interface`

import com.miftakhularzak.footballclubapp.model.Match

interface MatchView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data : List<Match>)
}
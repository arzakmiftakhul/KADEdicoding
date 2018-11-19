package com.miftakhularzak.footballclubapp.`interface`

import com.miftakhularzak.footballclubapp.model.Player

interface PlayerView {
    fun showLoading()
    fun hideLoading()
    fun showPlayerList(data: List<Player>)
}
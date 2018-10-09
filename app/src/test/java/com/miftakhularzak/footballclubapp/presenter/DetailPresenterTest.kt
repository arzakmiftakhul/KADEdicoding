package com.miftakhularzak.footballclubapp.presenter

import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.`interface`.DetailView
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.api.TheSportDBApi
import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.model.MatchResponse
import com.miftakhularzak.footballclubapp.model.Team
import com.miftakhularzak.footballclubapp.model.TeamResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class DetailPresenterTest {
    @Mock
    private
    lateinit var view: DetailView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: DetailPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = DetailPresenter(view, apiRepository,gson, TestContextProvider())
    }
    @Test
    fun testGetDetailMatch(){
        val events: MutableList<Match> = mutableListOf()
        val homeTeams: MutableList<Team> = mutableListOf()
        val awayTeams: MutableList<Team> = mutableListOf()
        val detailMatchResponse = MatchResponse(events)
        val homeBadgeResponse = TeamResponse(homeTeams)
        val awayBadgeResponse = TeamResponse(awayTeams)
        val eventId = "576536"
        val homeId = "133604"
        val awayId = "133624"

        `when`(gson.fromJson(apiRepository.
                doRequest(TheSportDBApi.getMatchDetail(eventId)),
                MatchResponse::class.java)).thenReturn(detailMatchResponse)
        `when`(gson.fromJson(apiRepository.
                doRequest(TheSportDBApi.getTeams(homeId)),
                TeamResponse::class.java)).thenReturn(homeBadgeResponse)
        `when`(gson.fromJson(apiRepository.
                doRequest(TheSportDBApi.getTeams(awayId)),
                TeamResponse::class.java)).thenReturn(awayBadgeResponse)

        presenter.getDetailMatch(eventId,homeId,awayId)

        verify(view).showLoading()
        verify(view).showDetailMatch(events,homeTeams,awayTeams)
        verify(view).hideLoading()
    }
}
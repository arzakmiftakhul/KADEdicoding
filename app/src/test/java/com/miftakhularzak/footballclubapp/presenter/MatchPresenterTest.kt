package com.miftakhularzak.footballclubapp.presenter

import com.google.gson.Gson
import com.miftakhularzak.footballclubapp.`interface`.MatchView
import com.miftakhularzak.footballclubapp.api.ApiRepository
import com.miftakhularzak.footballclubapp.api.TheSportDBApi
import com.miftakhularzak.footballclubapp.model.Match
import com.miftakhularzak.footballclubapp.model.MatchResponse
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class MatchPresenterTest {
    @Mock
    private
    lateinit var view : MatchView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    private lateinit var presenter: MatchPresenter

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        presenter = MatchPresenter(view, apiRepository, gson,TestContextProvider())
    }
    @Test
    fun testGetMatchList(){
        val events : MutableList<Match> = mutableListOf()
        val response = MatchResponse(events)
        val leagueId = "4328"
        val schEvents = "eventsnextleague.php"
        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.
                getMatch(leagueId,schEvents)),
                MatchResponse::class.java)).thenReturn(response)

        presenter.getMatchList(leagueId,schEvents)

        verify(view).showLoading()
        verify(view).showMatchList(events)
        verify(view).hideLoading()
    }
}
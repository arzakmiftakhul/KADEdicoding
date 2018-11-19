package com.miftakhularzak.footballclubapp.api

import com.miftakhularzak.footballclubapp.BuildConfig

object TheSportDBApi {
    fun getTeams(league: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}/"+"lookupteam.php?id="+league
    }
    fun getTeamList(leagueName: String?): String{
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}/search_all_teams.php?l=${leagueName}"
    }

    fun getMatch(leagueId: String?, events: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}/"+events+"id="+leagueId

    }
    fun getMatchDetail(eventId : String?):String{
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}/"+"lookupevent.php?id="+eventId
    }
    fun getPlayerList(teamId: String?): String{
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}/"+"lookup_all_players.php?id="+teamId
    }
    fun searchMatch(matchName: String?): String{
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}/"+"searchevents.php?e="+matchName
    }
    fun searchTeam(teamName: String?): String{
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}/"+"searchteams.php?t="+teamName
    }
}
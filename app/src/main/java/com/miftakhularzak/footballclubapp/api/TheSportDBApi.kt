package com.miftakhularzak.footballclubapp.api

import android.net.Uri
import com.miftakhularzak.footballclubapp.BuildConfig

object TheSportDBApi {
    fun getTeams(league: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath("lookupteam.php")
                .appendQueryParameter("id", league)
                .build()
                .toString()

    }

    fun getNextMatch(leagueId: String?, events: String?): String {
        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
                .appendPath("api")
                .appendPath("v1")
                .appendPath("json")
                .appendPath(BuildConfig.TSDB_API_KEY)
                .appendPath(events)
                .appendQueryParameter("id", leagueId)
                .build()
                .toString()

    }
}
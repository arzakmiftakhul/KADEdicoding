package com.miftakhularzak.footballclubapp.api

import com.miftakhularzak.footballclubapp.BuildConfig

object TheSportDBApi {
    fun getTeams(league: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}/"+"lookupteam.php?id="+league
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath("lookupteam.php")
//                .appendQueryParameter("id", league)
//                .build()
//                .toString()

    }

    fun getMatch(leagueId: String?, events: String?): String {
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}/"+events+"id="+leagueId
//        return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//                .appendPath("api")
//                .appendPath("v1")
//                .appendPath("json")
//                .appendPath(BuildConfig.TSDB_API_KEY)
//                .appendPath(events)
//                .appendQueryParameter("id", leagueId)
//                .build()
//                .toString()

    }
    fun getMatchDetail(eventId : String?):String{
        return BuildConfig.BASE_URL+"api/v1/json/${BuildConfig.TSDB_API_KEY}/"+"lookupevent.php?id="+eventId
//       return Uri.parse(BuildConfig.BASE_URL).buildUpon()
//               .appendPath("api")
//               .appendPath("v1")
//               .appendPath("json")
//               .appendPath(BuildConfig.TSDB_API_KEY)
//               .appendPath("lookupevent.php")
//               .appendQueryParameter("id",eventId)
//               .build()
//               .toString()
    }
}
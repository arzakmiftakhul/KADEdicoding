package com.miftakhularzak.footballclubapp.model

data class Favorite (val id: Long?,
                     val eventId : String?,
                     val homeId : String?,
                     val awayId : String?,
                     val homeTeam : String?,
                     val awayTeam : String?,
                     val homeScore : String?,
                     val awayScore : String?,
                     val dateEvent : String?,
                     val timeEvent : String?){
    companion object {
        const val TABLE_FAVORITE : String = "TABLE_MATCH_FAVORITE"
        const val ID :String = "ID_"
        const val EVENT_ID : String = "EVENT_ID"
        const val HOME_ID : String = "HOME_ID"
        const val AWAY_ID : String = "AWAY_ID"
        const val HOME_TEAM : String = "HOME_TEAM"
        const val AWAY_TEAM : String = "AWAY_TEAM"
        const val HOME_SCORE : String = "HOME_SCORE"
        const val AWAY_SCORE : String = "AWAY_SCORE"
        const val DATE_EVENT : String = "DATE_EVENT"
        const val TIME_EVENT : String = "TIME_EVENT"
    }
}
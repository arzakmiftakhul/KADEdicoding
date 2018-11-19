package com.miftakhularzak.footballclubapp.model

data class FavoriteTeam (val id: Long?,
                         val teamId : String?,
                         val teamName: String?,
                         val teamBadge : String?,
                         val teamOverview: String?,
                         val teamFormedYear: String?,
                         val teamStadium: String?,
                         val teamLeague: String?){
    companion object {
        const val TABLE_FAVORITE_TEAM : String = "TABLE_TEAM_FAVORITE"
        const val ID :String = "ID_"
        const val TEAM_ID : String = "TEAM_ID"
        const val TEAM_NAME : String = "TEAM_NAME"
        const val TEAM_BADGE : String = "TEAM_BADGE"
        const val TEAM_OVERVIEW : String = "TEAM_OVERVIEW"
        const val TEAM_FORMED_YEAR : String = "TEAM_FORMED_YEAR"
        const val TEAM_STADIUM : String = "TEAM_STADIUM"
        const val TEAM_LEAGUE : String = "TEAM_LEAGUE"
    }
}
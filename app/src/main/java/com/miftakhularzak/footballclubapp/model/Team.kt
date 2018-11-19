package com.miftakhularzak.footballclubapp.model

import com.google.gson.annotations.SerializedName

data class Team (
        @SerializedName("idTeam")
        var teamId: String? = null,
        @SerializedName("strTeam")
        var teamName: String? = null,
        @SerializedName("strTeamBadge")
        var teamBadge: String? = null,
        @SerializedName("intFormedYear")
        var teamFormedYear: String? = null,
        @SerializedName("strStadium")
        var teamStadium: String? = null,
        @SerializedName("strStadiumLocation")
        var teamStadiumLocation: String? = null,
        @SerializedName("strDescriptionEN")
        var teamOverview: String? = null,
        @SerializedName("strLeague")
        var teamLeague: String? = null
)
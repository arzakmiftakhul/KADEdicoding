package com.miftakhularzak.footballclubapp.model

import com.google.gson.annotations.SerializedName

data class Player (
        @SerializedName("idPlayer")
        var playerId: String? = null,
        @SerializedName ("strPlayer")
        var playerName: String? = null,
        @SerializedName ("dateBorn")
        var playerDateBorn: String? = null,
        @SerializedName ("strHeight")
        var playerHeight: String? = null,
        @SerializedName ("strWeight")
        var playerWeight: String? = null,
        @SerializedName ("strDescriptionEN")
        var playerDesc: String? = null,
        @SerializedName ("strPosition")
        var playerPosition: String? = null,
        @SerializedName ("strThumb")
        var playerPhoto: String? = null,
        @SerializedName ("strCutout")
        var playerCutout: String? = null
)
package com.movieapp.data.remote.entity


import com.google.gson.annotations.SerializedName

data class SessionRequestEntity(
    @SerializedName("request_token")
    val requestToken: String
)
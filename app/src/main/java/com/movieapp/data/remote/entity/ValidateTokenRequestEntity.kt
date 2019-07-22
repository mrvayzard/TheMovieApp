package com.movieapp.data.remote.entity

import com.google.gson.annotations.SerializedName

data class ValidateTokenRequestEntity(
    @SerializedName("username")
    val username: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("request_token")
    val requestToken: String
)
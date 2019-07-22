package com.movieapp.data.remote.entity


import com.google.gson.annotations.SerializedName

data class RequestTokenEntity(
    @SerializedName("expires_at")
    val expiresAt: String,
    @SerializedName("request_token")
    val requestToken: String,
    @SerializedName("success")
    val success: Boolean
)
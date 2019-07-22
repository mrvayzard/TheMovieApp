package com.movieapp.data.remote.entity


import com.google.gson.annotations.SerializedName

data class SessionResponseEntity(
    @SerializedName("session_id")
    val sessionId: String,
    @SerializedName("success")
    val success: Boolean
)
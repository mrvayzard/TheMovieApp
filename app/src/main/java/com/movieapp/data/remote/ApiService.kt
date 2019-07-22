package com.movieapp.data.remote

import com.movieapp.data.remote.entity.*
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @GET("/api")
    suspend fun getRandomUser(): Response<UserResponseEntity>

    @GET("/3/authentication/token/new")
    suspend fun createRequestToken(): Response<RequestTokenEntity>

    @POST("/3/authentication/token/validate_with_login")
    suspend fun validateRequestToken(
        @Body request: ValidateTokenRequestEntity
    ): Response<ValidateTokenResponseEntity>

    @POST("/3/authentication/session/new")
    suspend fun createNewSession(
        @Body request: SessionRequestEntity
    ): Response<SessionResponseEntity>
}
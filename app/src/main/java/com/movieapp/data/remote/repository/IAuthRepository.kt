package com.movieapp.data.remote.repository

import com.movieapp.data.Result

interface IAuthRepository {
    suspend fun createRequestToken(): Result<String>
    suspend fun validateRequestToken(
        username: String, password: String,
        requestToken: String
    ): Result<String>

    suspend fun createNewSession(validatedRequestToken: String): Result<String>

    suspend fun loginUser(username: String, password: String): Result<String>
}

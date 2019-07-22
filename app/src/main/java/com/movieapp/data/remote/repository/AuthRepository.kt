package com.movieapp.data.remote.repository

import com.movieapp.data.Result
import com.movieapp.data.ResultError
import com.movieapp.data.ResultSuccess
import com.movieapp.data.mapTo
import com.movieapp.data.remote.ApiService
import com.movieapp.data.remote.entity.SessionRequestEntity
import com.movieapp.data.remote.entity.ValidateTokenRequestEntity

class AuthRepository(
    private val apiService: ApiService
) : BaseRepository(), IAuthRepository {
    override suspend fun createRequestToken(): Result<String> {
        val result = processRequest {
            apiService.createRequestToken()
        }
        if (result is ResultSuccess && !result.data.success) {
            return ResultError(message = "Token was not created.")
        }
        return result.mapTo { it.requestToken }
    }

    override suspend fun validateRequestToken(
        username: String,
        password: String,
        requestToken: String
    ): Result<String> {
        val result = processRequest {
            apiService.validateRequestToken(
                ValidateTokenRequestEntity(username, password, requestToken)
            )
        }
        if (result is ResultSuccess && !result.data.success) {
            return ResultError(message = "Request token was not validated.")
        }
        return result.mapTo { it.requestToken }
    }

    override suspend fun createNewSession(
        validatedRequestToken: String
    ): Result<String> {
        val result = processRequest {
            apiService.createNewSession(
                SessionRequestEntity(validatedRequestToken)
            )
        }
        if (result is ResultSuccess && !result.data.success) {
            return ResultError(message = "Session was not created.")
        }
        return result.mapTo { it.sessionId }
    }

    override suspend fun loginUser(
        username: String,
        password: String
    ): Result<String> {
        val requestTokenResult = createRequestToken()
        return if (requestTokenResult is ResultSuccess) {
            val validationResult = validateRequestToken(username, password, requestTokenResult.data)
            if (validationResult is ResultSuccess) {
                createNewSession(validationResult.data)
            } else {
                validationResult
            }
        } else {
            requestTokenResult
        }
    }
}
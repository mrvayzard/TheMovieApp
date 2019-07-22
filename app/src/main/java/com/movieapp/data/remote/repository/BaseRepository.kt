package com.movieapp.data.remote.repository

import com.movieapp.base.exception.ApiKeyException
import com.movieapp.base.exception.NotFoundException
import com.movieapp.base.exception.ServerException
import com.movieapp.base.exception.UnknownException
import com.movieapp.data.Result
import com.movieapp.data.ResultError
import com.movieapp.data.ResultSuccess
import retrofit2.Response

open class BaseRepository {
    suspend fun <T : Any> processRequest(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()
            if (response.isSuccessful) ResultSuccess(response.body()!!)
            else {
                when (response.code()) {
                    500 -> ResultError(exception = ServerException())
                    401 -> ResultError(exception = ApiKeyException())
                    404 -> ResultError(exception = NotFoundException())
                    else -> ResultError(exception = UnknownException())
                }
            }
        } catch (ex: Exception) {
            ResultError(exception = ex)
        }
    }
}

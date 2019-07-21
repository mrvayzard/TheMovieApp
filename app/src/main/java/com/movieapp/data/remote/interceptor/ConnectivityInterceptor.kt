package com.movieapp.data.remote.interceptor

import android.content.Context
import com.movieapp.base.exception.NoInternetException
import com.movieapp.util.NetworkUtil
import okhttp3.Interceptor
import okhttp3.Response

class ConnectivityInterceptor(
    private val context: Context
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!NetworkUtil.isNetworkConnected(context)) {
            throw NoInternetException()
        }

        return chain.proceed(chain.request())
    }
}
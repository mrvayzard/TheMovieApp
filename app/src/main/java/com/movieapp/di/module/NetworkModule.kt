package com.movieapp.di.module

import android.location.Geocoder
import com.movieapp.BuildConfig
import com.movieapp.data.remote.ApiService
import com.movieapp.data.remote.interceptor.ConnectivityInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object NetworkModule {
    val module = module {
        single { createOkHttpClient(get()) }
        single { createRetrofitInstance(get()) }
        single { createApiService(get()) }
        single { Geocoder(androidContext()) }
        single { ConnectivityInterceptor(androidContext()) }
    }

    private fun createAppAuthInterceptor() = Interceptor { chain ->
        val url = chain.request().url()
            .newBuilder()
            .addQueryParameter("api_key", BuildConfig.API_KEY)
            .build()
        val request = chain.request().newBuilder().url(url).build()
        chain.proceed(request)
    }


    private fun createOkHttpClient(
        connectivityInterceptor: ConnectivityInterceptor
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val builder = OkHttpClient.Builder()
            .addInterceptor(connectivityInterceptor)
            .addInterceptor(createAppAuthInterceptor())
            .readTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

    private fun createRetrofitInstance(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    private fun createApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
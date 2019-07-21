package com.movieapp.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.os.Build

object NetworkUtil {
    private const val CONNECTION_TYPE_NONE = 0
    private const val CONNECTION_TYPE_MOBILE_DATA = 1
    private const val CONNECTION_TYPE_WIFI = 2

    fun isNetworkConnected(context: Context): Boolean {
        return getConnectionType(context) != CONNECTION_TYPE_NONE
    }

    @Suppress("DEPRECATION")
    private fun getConnectionType(context: Context): Int {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager?
            ?: return CONNECTION_TYPE_NONE

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val networkCapabilities = cm.getNetworkCapabilities(cm.activeNetwork)
            if (networkCapabilities != null
                && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                && networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)
            ) {
                when {
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> return CONNECTION_TYPE_WIFI
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> return CONNECTION_TYPE_MOBILE_DATA
                }
            }
        } else {
            val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
            if (activeNetwork?.isConnected == true) {
                when (activeNetwork.type) {
                    ConnectivityManager.TYPE_WIFI -> return CONNECTION_TYPE_WIFI
                    ConnectivityManager.TYPE_MOBILE -> return CONNECTION_TYPE_MOBILE_DATA
                }
            }
        }
        return CONNECTION_TYPE_NONE
    }
}

package com.hariagus.submission2bajp.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

object NetworkHelper {

    fun isNetworkConnected(context: Context): Boolean {
        var result = false
        (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                result = isCapableNetwork(this, this.activeNetwork)
            } else {
                val networkInfo = this.allNetworks
                for (tempNetworkInfo in networkInfo) {
                    if (isCapableNetwork(this, tempNetworkInfo)) {
                        result = true
                    }
                }
            }
        }
        return result
    }

    private fun isCapableNetwork(cManager: ConnectivityManager, network: Network?): Boolean {
        cManager.getNetworkCapabilities(network)?.also {
            if (it.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                return true
            } else if (it.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                return true
            }
        }
        return false
    }

}
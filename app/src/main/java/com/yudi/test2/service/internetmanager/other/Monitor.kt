package com.yudi.test2.service.internetmanager.other

/**
 * @author Yudi Rahmat
 */

interface Monitor :
    LifecycleListener {
    interface ConnectivityListener {
        fun onConnectivityChanged(
            connectionType: Int,
            isConnected: Boolean,
            isFast: Boolean
        )
    }
}
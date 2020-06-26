package com.yudi.test2.service.internetmanager.component

import com.yudi.test2.service.internetmanager.other.Monitor

/**
 * @author Yudi Rahmat
 */

class NoopMonitor :
    Monitor {
    override fun onStart() { //no-op
    }

    override fun onStop() { //no-op
    }
}
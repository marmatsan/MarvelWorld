package com.mango.marvelworld.domain.utils.connectivityobserver

import kotlinx.coroutines.flow.Flow

interface ConnectivityObserver {
    fun observe() : Flow<ConnectiviyStatus>

    enum class ConnectiviyStatus {
        AVAILABLE,
        UNAVAILABLE,
        LOSING,
        LOST
    }
}
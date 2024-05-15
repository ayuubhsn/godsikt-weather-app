package com.example.IN2000_prosjekt.model.Internett

import kotlinx.coroutines.flow.Flow

interface NetworkObserver {
    fun oberrver(): Flow<Status>

    enum class Status{
        Tilgjengelig, Utilgjengelig
    }
}

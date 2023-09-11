package com.example.statemachine.mocknetwork

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface StateRepository {
    fun getState(): Flow<String>
}

class StateRepositoryImpl : StateRepository {
    private val values = listOf("a", "b", "c", "d")
    override fun getState(): Flow<String> = flow {
        // Randomly emit a state every 1 second to simulate a state machine
        for (i in (0..1000)) {
            emit(values.random())
            delay(1000)
        }
    }

}
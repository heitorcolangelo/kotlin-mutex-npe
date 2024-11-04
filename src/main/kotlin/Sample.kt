package com.adaptics.kitchenos.sdk.token.provider.data

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class SharedResource(coroutineScope: CoroutineScope) {

    init {
        coroutineScope.launch {
            criticalSection1()
            criticalSection2()
        }
    }

    private val mutex = Mutex()

    private suspend fun criticalSection1() = mutex.withLock {
        println("Critical Section 1")
        delay(1000)
    }

    private suspend fun criticalSection2() = mutex.withLock {
        println("Critical Section 2")
        delay(1000)
    }

}

fun main() = runBlocking {
    SharedResource(CoroutineScope(Dispatchers.Default))
    delay(2000)
}
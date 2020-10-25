package com.tuankhaiit.util.operation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import kotlinx.coroutines.*

/**
 * Created by tuankhaiit on 10/20/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

fun <T> LiveData<T>.debounce(
    duration: Long = 1000L,
    coroutineScope: CoroutineScope = MainScope()
) = MediatorLiveData<T>().also { mld ->
    val source = this
    var job: Job? = null

    mld.addSource(source) {
        job?.cancel()
        job = coroutineScope.launch {
            delay(duration)
            mld.value = source.value
        }
    }
}

fun <T> coroutinesDebounce(
    delayMillis: Long = 0L,
    coroutineScope: CoroutineScope = MainScope(),
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (debounceJob == null) {
            debounceJob = coroutineScope.launch {
                action(param)
                delay(delayMillis)
                debounceJob = null
            }
        }
    }
}

fun <T> coroutinesDebounceUntilLast(
    delayMillis: Long = 0L,
    coroutineScope: CoroutineScope = MainScope(),
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = coroutineScope.launch {
            delay(delayMillis)
            action(param)
        }
    }
}
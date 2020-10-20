package com.tuankhaiit.util.operation

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by tuankhaiit on 10/20/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

fun <T> coroutinesDebounce(
    delayMillis: Long = 0L,
    scope: CoroutineScope,
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        if (debounceJob == null) {
            debounceJob = scope.launch {
                action(param)
                delay(delayMillis)
                debounceJob = null
            }
        }
    }
}

fun <T> coroutinesDebounceUntilLast(
    delayMillis: Long = 0L,
    scope: CoroutineScope,
    action: (T) -> Unit
): (T) -> Unit {
    var debounceJob: Job? = null
    return { param: T ->
        debounceJob?.cancel()
        debounceJob = scope.launch {
            delay(delayMillis)
            action(param)
        }
    }
}
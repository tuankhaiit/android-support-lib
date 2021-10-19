package com.tuankhaiit.util.view.listener

import android.view.View
import com.tuankhaiit.util.operator.coroutinesDebounce
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope

/**
 * Created by tuankhaiit on 10/20/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

fun View.setDebounceOnClickListener(
    delayMillis: Long = 500L,
    coroutineScope: CoroutineScope = MainScope(),
    action: (view: View) -> Unit
) {
    val clickWithDebounce: (view: View) -> Unit =
        coroutinesDebounce(delayMillis = delayMillis, coroutineScope = coroutineScope) {
            action(it)
        }

    setOnClickListener(clickWithDebounce)
}
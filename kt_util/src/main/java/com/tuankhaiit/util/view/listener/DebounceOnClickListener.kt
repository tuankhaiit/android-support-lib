package com.tuankhaiit.util.view.listener

import android.view.View
import com.tuankhaiit.util.operation.coroutinesDebounce
import kotlinx.coroutines.MainScope

/**
 * Created by tuankhaiit on 10/20/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

fun View.setDebounceOnClickListener(
    delayMillis: Long = 500L,
    action: (view: View) -> Unit
) {
    val scope = MainScope()
    val clickWithDebounce: (view: View) -> Unit =
        coroutinesDebounce(delayMillis = delayMillis, scope = scope) {
            action(it)
        }

    setOnClickListener(clickWithDebounce)
}
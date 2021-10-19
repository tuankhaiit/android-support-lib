package com.tuankhaiit.util.animation

import android.view.View
import android.view.animation.AlphaAnimation
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * Created by tuankhaiit on 10/20/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

private const val DEFAULT_FADE_IN_DURATION = 500L

fun View.animFadeIn(
    delayMillis: Long = 0L,
    fadeDuration: Long = DEFAULT_FADE_IN_DURATION,
    coroutineScope: CoroutineScope = MainScope(),
) {
    visibility = View.INVISIBLE
    coroutineScope.launch {
        delay(delayMillis)
        this@animFadeIn.startAnimation(AlphaAnimation(0F, 1F).apply {
            duration = fadeDuration
            fillAfter = true
        })
        visibility = View.VISIBLE
    }
}
package com.tuankhaiit.util.animation

import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AlphaAnimation

/**
 * Created by tuankhaiit on 10/20/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

private const val DEFAULT_FADE_IN_DURATION = 500L

fun View.animFadeIn(
    delayMillis: Long = 0L,
    fadeDuration: Long = DEFAULT_FADE_IN_DURATION
) {
    visibility = View.INVISIBLE
    Handler(Looper.getMainLooper()).postDelayed({
        this.startAnimation(AlphaAnimation(0F, 1F).apply {
            duration = fadeDuration
            fillAfter = true
        })
        visibility = View.VISIBLE
    }, delayMillis)
}
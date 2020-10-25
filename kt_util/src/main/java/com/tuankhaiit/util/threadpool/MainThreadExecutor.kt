package com.tuankhaiit.util.threadpool

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor

/**
 * Created by tuankhaiit on 10/25/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

class MainThreadExecutor : Executor {
    private var handler = Handler(Looper.getMainLooper())

    override fun execute(command: Runnable) {
        handler.post(command)
    }

    private fun executeDelay(command: Runnable, delay: Long) {
        handler.postDelayed(command, delay)
    }

    fun run(executor: () -> Unit) {
        val command = Runnable { executor() }
        execute(command)
    }

    fun run(executor: () -> Unit, delay: Long = 0) {
        val command = Runnable { executor() }
        executeDelay(command, delay)
    }
}
package com.tuankhaiit.util.view.jobschedule

import android.view.Choreographer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by tuankhaiit on 10/25/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

object UIJobScheduler {
    private const val MAX_JOB_TIME_MS: Float = 4f

    private var elapsed = 0L
    private val jobQueue = ArrayDeque<() -> Unit>()
    private val isOverMaxTime get() = elapsed > MAX_JOB_TIME_MS * 1_000_000

    fun submitJob(coroutineScope: CoroutineScope = MainScope(), job: () -> Unit) {
        jobQueue.add(job)
        if (jobQueue.size == 1) {
            coroutineScope.launch {
                processJobs()
            }
        }
    }

    private fun processJobs() {
        while (!jobQueue.isEmpty() && !isOverMaxTime) {
            val start = System.nanoTime()
            jobQueue.poll()?.invoke()
            elapsed += System.nanoTime() - start
        }
        if (jobQueue.isEmpty()) {
            elapsed = 0
        } else if (isOverMaxTime) {
            onNextFrame {
                elapsed = 0
                processJobs()
            }
        }
    }

    private fun onNextFrame(callback: () -> Unit) {
        Choreographer.getInstance().postFrameCallback { callback() }
    }
}
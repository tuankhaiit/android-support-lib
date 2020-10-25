package com.tuankhaiit.util.threadpool

import android.os.Process
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.PriorityBlockingQueue
import java.util.concurrent.TimeUnit

class KTExecutorSupplier {
    private var forBackgroundTasks: PriorityThreadPoolExecutor
    private var forLightWeightBackgroundTasks: PriorityThreadPoolExecutor
    private var forSingleTasks: PriorityThreadPoolExecutor
    private var mainThreadExecutor: MainThreadExecutor

    init {
        val backgroundPriority = PriorityThreadFactory(Process.THREAD_PRIORITY_BACKGROUND)
        forBackgroundTasks = PriorityThreadPoolExecutor(
            NUMBER_OF_CORES * 2,
            NUMBER_OF_CORES * 2,
            60L,
            TimeUnit.SECONDS,
            PriorityBlockingQueue<Runnable>(),
            backgroundPriority
        )

        forLightWeightBackgroundTasks = PriorityThreadPoolExecutor(
                NUMBER_OF_CORES * 2,
                NUMBER_OF_CORES * 2,
                60L,
                TimeUnit.SECONDS,
                PriorityBlockingQueue<Runnable>(),
                backgroundPriority
            )

        forSingleTasks = PriorityThreadPoolExecutor(
            1,
            1,
            60L,
            TimeUnit.SECONDS,
            LinkedBlockingQueue<Runnable>(),
            backgroundPriority
        )

        mainThreadExecutor = MainThreadExecutor()
    }

    object Holder {
        val instance = KTExecutorSupplier()
    }

    companion object {
        val NUMBER_OF_CORES
            get() = Runtime.getRuntime().availableProcessors()
        private val instance = Holder.instance

        fun getInstance() = instance
    }

    fun forBackgroundTasks() = forBackgroundTasks
    fun forLightWeightBackgroundTasks() = forLightWeightBackgroundTasks
    fun forMainThreadTasks() = mainThreadExecutor
    fun forSingleTasks() = forSingleTasks
}
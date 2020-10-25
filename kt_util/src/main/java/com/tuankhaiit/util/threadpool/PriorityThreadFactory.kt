package com.tuankhaiit.util.threadpool

import android.os.Process
import java.util.concurrent.ThreadFactory

/**
 * Created by tuankhaiit on 10/25/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

class PriorityThreadFactory(private val threadPriority: Int) : ThreadFactory {

    override fun newThread(runable: Runnable?): Thread {
        val wrapperRunnable = Runnable {
            try {
                Process.setThreadPriority(threadPriority)
            } catch (t: Throwable) {
            }
            runable?.run()
        }
        return Thread(wrapperRunnable)
    }
}
package com.tuankhaiit.util.view.adapter

import androidx.recyclerview.widget.RecyclerView
import com.tuankhaiit.util.view.adapter.holder.LifecycleViewHolder

/**
 * Created by tuankhaiit on 23/09/2021.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

abstract class BaseAdapter<VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {
    override fun onViewAttachedToWindow(holder: VH) {
        super.onViewAttachedToWindow(holder)
        if (holder is LifecycleViewHolder) {
            holder.onAppear()
        }
    }

    override fun onViewDetachedFromWindow(holder: VH) {
        super.onViewDetachedFromWindow(holder)
        if (holder is LifecycleViewHolder) {
            holder.onDisappear()
        }
    }
}
package com.tuankhaiit.util.view.adapter

import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.tuankhaiit.util.view.adapter.holder.LifecycleViewHolder

/**
 * Created by tuankhaiit on 23/09/2021.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

abstract class BaseListAdapter<T, VH : RecyclerView.ViewHolder> :
    androidx.recyclerview.widget.ListAdapter<T, VH> {
    constructor(diffCallback: DiffUtil.ItemCallback<T>) : super(diffCallback)
    constructor(config: AsyncDifferConfig<T>) : super(config)

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
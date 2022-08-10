package com.tuankhaiit.shared.presentation.binding

import android.view.View
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.databinding.BindingAdapter

/**
 * Created by tuankhaiit on 23/09/2021.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

@BindingAdapter("goneUnlessOnMotionLayout")
fun goneUnlessOnMotionLayout(view: View?, visible: Boolean) {
    if (view?.parent is MotionLayout) {
        val layout = view.parent as MotionLayout
        val setToVisibility =
            if (visible) View.VISIBLE else View.GONE
        val setToAlpha = if (visible) 1f else 0f
        for (constraintId in layout.constraintSetIds) {
            val constraint = layout.getConstraintSet(constraintId)
            if (constraint != null) {
                constraint.setVisibility(view.id, setToVisibility)
                constraint.setAlpha(view.id, setToAlpha)
            }
        }
    }
}
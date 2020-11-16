package com.tuankhaiit.util.system

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.*
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.children

/**
 * Created by tuankhaiit on 11/2/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

fun Context.getNearestActivity(): Activity? {
    var retries = 0
    var context: Context? = this
    while (context is ContextWrapper && retries < 10) {
        if (context is Activity) {
            return context
        }
        if (context.baseContext is Activity) {
            return context.baseContext as? Activity
        }
        context = context.baseContext
        retries++
    }
    return null
}

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let {
        imm.hideSoftInputFromWindow(it.windowToken, 0)
    }
}

fun Context.hideKeyboard() {
    getNearestActivity()?.hideKeyboard()
}

fun Activity.showKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    currentFocus?.let {
        imm.showSoftInput(currentFocus, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Context.showKeyboard() {
    getNearestActivity()?.showKeyboard()
}

fun View.hideKeyboardWhenTouchOutside() {
    context.getNearestActivity()?.let {
        setupHideKeyboardWhenTouchOutside(this, it)
    }
}

@SuppressLint("ClickableViewAccessibility")
private fun setupHideKeyboardWhenTouchOutside(view: View, activity: Activity?) {
    if (view !is EditText) {
        view.setOnTouchListener { _, _ ->
            activity?.hideKeyboard()
            return@setOnTouchListener false
        }
    }
    if (view is ViewGroup) {
        for (child in view.children) {
            setupHideKeyboardWhenTouchOutside(child, activity)
        }
    }
}


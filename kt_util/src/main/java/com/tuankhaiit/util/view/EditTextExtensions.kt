package com.tuankhaiit.util.view

import android.content.Context
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView

/**
 * Created by tuankhaiit on 11/16/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

fun EditText.requestFocusAndShowKeyboard() {
    requestFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun EditText.clearFocusAndHideKeyboard() {
    clearFocus()
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager?
    imm?.hideSoftInputFromWindow(windowToken, 0)
}
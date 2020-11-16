package com.tuankhaiit.util.view

import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView

/**
 * Created by tuankhaiit on 11/16/20.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

fun TextView.disableCopyPaste() {
    isLongClickable = false
    setTextIsSelectable(false)
    customSelectionActionModeCallback = object : ActionMode.Callback {
        override fun onCreateActionMode(mode: ActionMode?, menu: Menu): Boolean {
            return false
        }

        override fun onPrepareActionMode(mode: ActionMode?, menu: Menu): Boolean {
            return false
        }

        override fun onActionItemClicked(mode: ActionMode?, item: MenuItem): Boolean {
            return false
        }

        override fun onDestroyActionMode(mode: ActionMode?) {}
    }
}
package com.tuankhaiit.util.view

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

/**
 * Created by tuankhaiit on 23/09/2021.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

fun AppCompatActivity.addFragment(containerId: Int, fragment: Fragment, tag: String? = null) {
    supportFragmentManager.beginTransaction()
        .add(containerId, fragment, tag)
        .apply {
            if (tag != null) {
                this.addToBackStack(tag)
            }
        }
        .commitAllowingStateLoss()
}

fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment, tag: String? = null) {
    supportFragmentManager.beginTransaction()
        .replace(containerId, fragment, tag)
        .apply {
            if (tag != null) {
                this.addToBackStack(tag)
            }
        }
        .commitAllowingStateLoss()
}

fun Fragment.replaceFragment(containerId: Int, fragment: Fragment, tag: String? = null) {
    childFragmentManager.beginTransaction()
        .replace(containerId, fragment, tag)
        .apply {
            if (tag != null) {
                this.addToBackStack(tag)
            }
        }
        .commitAllowingStateLoss()
}

fun Fragment.addFragment(containerId: Int, fragment: Fragment, tag: String? = null) {
    childFragmentManager.beginTransaction()
        .add(containerId, fragment, tag)
        .apply {
            if (tag != null) {
                this.addToBackStack(tag)
            }
        }
        .commitAllowingStateLoss()
}

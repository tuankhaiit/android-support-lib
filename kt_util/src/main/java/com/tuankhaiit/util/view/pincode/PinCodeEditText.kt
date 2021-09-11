package com.tuankhaiit.util.view.pincode

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.text.InputFilter
import android.text.InputType
import android.util.AttributeSet
import android.util.Log
import android.util.TypedValue
import android.view.Gravity
import android.view.KeyEvent
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import com.tuankhaiit.util.R
import com.tuankhaiit.util.view.clearFocusAndHideKeyboard
import com.tuankhaiit.util.view.disableCopyPaste
import com.tuankhaiit.util.view.requestFocusAndShowKeyboard


/**
 * Created by tuankhaiit on 10/09/2021.
 * tuankhai0811@gmail.com
 * https://www.tuankhaiit.com
 */

class PinCodeEditText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttrs: Int = 0,
) : LinearLayout(context, attrs, defStyleAttrs) {
    var onCodeChangeListener: OnCodeChangeListener? = null

    private var digits = arrayListOf<AppCompatEditText>()
    private var pinLength = 6
    private var pinSpacing = 20.0f
    private var pinPadding = 20.0f
    private var pinTextSize = -1
    private var pinTextColor = Color.BLACK
    private var pinBackground: Int = -1

    init {
        orientation = HORIZONTAL
        gravity = Gravity.CENTER
        setOnClickListener {
            focusActualDigit()
        }

        val typeArray = context.obtainStyledAttributes(attrs, R.styleable.PinCodeEditText)
        try {
            pinLength = typeArray.getInteger(R.styleable.PinCodeEditText_pinLength, pinLength)
            pinSpacing = typeArray.getDimension(R.styleable.PinCodeEditText_pinSpacing, pinSpacing)
            pinPadding = typeArray.getDimension(R.styleable.PinCodeEditText_pinPadding, pinPadding)
            pinTextSize =
                typeArray.getDimensionPixelSize(
                    R.styleable.PinCodeEditText_pinTextSize,
                    pinTextSize
                )
            pinTextColor =
                typeArray.getColor(R.styleable.PinCodeEditText_pinTextColor, pinTextColor)
            pinBackground =
                typeArray.getResourceId(R.styleable.PinCodeEditText_pinBackgroundDrawable, -1)
        } finally {
            typeArray.recycle()
        }

        addDigits()
        setupListener()
    }

    private fun addDigits() {
        for (i in 1..pinLength) {
            val digit = AppCompatEditText(context).apply {
                setText("1")
                maxLines = 1
                movementMethod = null
                isCursorVisible = false
                gravity = Gravity.CENTER
                inputType = InputType.TYPE_CLASS_NUMBER
                filters = arrayOf(InputFilter.LengthFilter(1))
                if (pinBackground != -1) {
                    setBackgroundResource(pinBackground)
                }
                setTextColor(pinTextColor)
                setPadding(
                    pinPadding.toInt(),
                    pinPadding.toInt(),
                    pinPadding.toInt(),
                    pinPadding.toInt()
                )
                val layoutParams =
                    LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
                        leftMargin = pinSpacing.toInt() / 2
                        rightMargin = pinSpacing.toInt() / 2
                        gravity = Gravity.CENTER
                    }
                if (pinTextSize != -1) {
                    setTextSize(TypedValue.COMPLEX_UNIT_PX, pinTextSize.toFloat())
                    layoutParams.width = pinTextSize + pinPadding.toInt() * 2
                }
                setLayoutParams(layoutParams)
                disableCopyPaste()
            }
            digits.add(digit)
            addView(digit)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupListener() {
        digits.forEachIndexed { index, digit ->
            digit.setOnFocusChangeListener { _, hasFocus ->
                if (hasFocus) {
                    focusActualDigit(index)
                }
            }
            digit.setOnKeyListener { _, keyCode, event ->
                Log.e("khaitdt", "$keyCode")
                if (event.action == KeyEvent.ACTION_DOWN) {
                    when (keyCode) {
                        KeyEvent.KEYCODE_DEL -> {
                            if (index > 0) {
                                if (digit.length() == 0) {
                                    digits[index - 1].setText("")
                                    digits[index - 1].requestFocus()
                                }
                            }
                            digit.setText("")
                            onCodeChangeListener?.onPinCodeChange(getPinCode())
                        }
                        KeyEvent.KEYCODE_0,
                        KeyEvent.KEYCODE_1,
                        KeyEvent.KEYCODE_2,
                        KeyEvent.KEYCODE_3,
                        KeyEvent.KEYCODE_4,
                        KeyEvent.KEYCODE_5,
                        KeyEvent.KEYCODE_6,
                        KeyEvent.KEYCODE_7,
                        KeyEvent.KEYCODE_8,
                        KeyEvent.KEYCODE_9 -> {
                            if (digit.length() == 0) {
                                digit.setText("${keyCode - KeyEvent.KEYCODE_0}")
                            }
                            if (index < digits.size - 1) {
                                digits[index + 1].requestFocus()
                            }
                            onCodeChangeListener?.onPinCodeChange(getPinCode())
                        }
                        KeyEvent.KEYCODE_ENTER -> {
                            if (digit.length() > 0 && index == digits.size - 1) {
                                digit.clearFocusAndHideKeyboard()
                            }
                        }
                    }
                }
                return@setOnKeyListener true
            }
        }
    }

    private fun findActualIndex(): Int {
        digits.forEachIndexed { index, digit ->
            if (digit.text.toString().isEmpty()) {
                return index
            }
        }
        return digits.size - 1
    }

    private fun focusActualDigit(index: Int = 0) {
        val actualIndex = findActualIndex()
        val actualDigit = digits[actualIndex]
        if (actualIndex != index) {
            actualDigit.requestFocusAndShowKeyboard()
        }
        actualDigit.setSelection(if (actualDigit.length() == 0) 0 else 1)
    }

    fun getPinCode(): String {
        return buildString {
            digits.forEach { digit ->
                if (digit.length() > 0) {
                    append(digit.text.toString())
                }
            }
        }
    }

    interface OnCodeChangeListener {
        fun onPinCodeChange(pinCode: String)
    }

}
package com.tuankhaiit.androidsupportlib

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.tuankhaiit.util.animation.animFadeIn
import com.tuankhaiit.util.view.listener.setDebounceOnClickListener
import com.tuankhaiit.util.view.pincode.PinCodeEditText
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pinCode.onCodeChangeListener = object : PinCodeEditText.OnCodeChangeListener {
            override fun onPinCodeChange(code: String) {
                if (code.length < 6) {
                    pinCode.error = false
                }
            }

            override fun onPinCodeDone(code: String) {
                pinCode.error = true
            }

        }

//        btnClicked.setDebounceOnClickListener {
//            count++
//            log()
//        }

//        btnEncryptedTest?.setDebounceOnClickListener {
//            val intent = Intent(this, EncryptedSharedPreferencesTestActivity::class.java)
//            startActivity(intent)
//        }

        txtText.animFadeIn(fadeDuration = 2000L)
    }

    private fun log() {
        Log.e("MainActivity", "count = $count")
    }
}
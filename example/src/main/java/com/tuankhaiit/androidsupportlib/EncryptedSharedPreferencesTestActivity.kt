package com.tuankhaiit.androidsupportlib

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.tuankhaiit.util.secure.sharedpreferences.KTEncryptedSharedPreferences
import kotlinx.android.synthetic.main.activity_encrypted_shared_preferences_test.*

class EncryptedSharedPreferencesTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encrypted_shared_preferences_test)

        btnWrite?.setOnClickListener {
            val user = User(
                name = edtName?.text.toString(),
                email = edtEmail?.text.toString(),
                address = edtAddress?.text.toString()
            )
            val users = arrayListOf<User>(user, user)
            KTEncryptedSharedPreferences.get(this).putObject("user", users)
        }

        btnRead?.setOnClickListener {
            txtValue?.text =
                KTEncryptedSharedPreferences.get(this).getObject<List<User>>("user").toString()
        }
    }

}
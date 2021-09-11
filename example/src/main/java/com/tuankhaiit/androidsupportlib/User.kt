package com.tuankhaiit.androidsupportlib

import java.io.Serializable

data class User(
    var name: String?,
    var email: String?,
    var address: String?
) : Serializable {

}
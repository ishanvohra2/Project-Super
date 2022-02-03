package com.ishanvohra.projectsuper.util

import android.util.Log
import java.math.BigInteger
import java.security.MessageDigest

object Util {

    fun md5(input:String): String {
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }

    fun timeStamp(): String{
        //getting time stamp
        val tsLong = System.currentTimeMillis() / 1000
        return tsLong.toString()
    }

}
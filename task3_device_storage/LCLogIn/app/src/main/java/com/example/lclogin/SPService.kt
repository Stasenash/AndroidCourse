package com.example.lclogin

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import androidx.fragment.app.FragmentActivity

class SPService constructor(private val activity: FragmentActivity?) {
    companion object {
        const val APP_PREFERENCES = "userSettings"
        var mSettings: SharedPreferences? = null

        const val APP_PREFERENCES_LOGIN = "Login"
        const val APP_PREFERENCES_PASSWORD = "Password"
        const val LOGIN = "stasenash"
        const val PASSWORD = "123456"

    }

    fun saveAuth() {
        mSettings = activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor = mSettings!!.edit()
        editor.putString(APP_PREFERENCES_LOGIN, LOGIN)
        editor.putString(APP_PREFERENCES_PASSWORD, PASSWORD)
        editor.apply()
    }

    fun delAuth() {
        mSettings = activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor = mSettings!!.edit()
        editor.clear()
        editor.apply()
    }

    fun contains(str : String): Boolean? {
        mSettings = activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        return mSettings?.contains(str)
    }
}
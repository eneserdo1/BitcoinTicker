package com.app.bitcointicker.data.local

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager.getDefaultSharedPreferences

class PreferencesHelper private constructor(context: Context) {

    private val mPreferences: SharedPreferences = getDefaultSharedPreferences(context)
    private val mEditor: SharedPreferences.Editor = mPreferences.edit()

    companion object {

        private var instance: PreferencesHelper? = null

        const val PROP_DEVICE_ID: String = "prop.device.id"
        const val PROP_USER_ID: String = "prop.user.id"
        const val PROP_REFRESH_TIME: String = "prop.refresh.time"

        fun getInstance(context: Context): PreferencesHelper {
            if (instance == null)
                instance = PreferencesHelper(context)
            return instance as PreferencesHelper
        }
    }

    var userId: String
        get() = mPreferences.getString(PROP_USER_ID, "")!!
        set(value) {
            mEditor.putString(PROP_USER_ID, value)
            mEditor.commit()
        }
    var favCoinId: String
        get() = mPreferences.getString(PROP_DEVICE_ID, "")!!
        set(value) {
            mEditor.putString(PROP_DEVICE_ID, value)
            mEditor.commit()
        }
    var refreshTime: String
        get() = mPreferences.getString(PROP_REFRESH_TIME, "")!!
        set(value) {
            mEditor.putString(PROP_REFRESH_TIME, value)
            mEditor.commit()
        }


    fun clear() {
        mEditor.clear()
        mEditor.commit()
    }


}
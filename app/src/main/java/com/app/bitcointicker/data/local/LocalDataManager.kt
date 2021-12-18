package com.app.bitcointicker.data.local

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast

class LocalDataManager {

    // Favoriye eklenilen coinin id'si sharedpreference'e kayıt ediliyor
    fun setSharedPreferences(context: Context, key:String, value:String){
        val sharedPref:SharedPreferences = context.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)
        val edit:SharedPreferences.Editor=sharedPref.edit()
        edit.putString(key,value)
        edit.commit()
    }

    // Favoriye eklenilen coinin sharedpreference'den çekiliyor
    fun getSharedPreference(context: Context,key: String):String{
        return context.getSharedPreferences(context.packageName,Context.MODE_PRIVATE).getString(key,"").toString()
    }

    // Favoriden kaldırılan item sharedpreferenceden siliniyor
    fun removeSharedprefence(context: Context,key: String){
        val sharedPref=context.getSharedPreferences(context.packageName,Context.MODE_PRIVATE)
        val edit:SharedPreferences.Editor=sharedPref.edit()
        edit.remove(key)
        edit.commit()
    }
}
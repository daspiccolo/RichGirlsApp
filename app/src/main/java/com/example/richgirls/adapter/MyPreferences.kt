package com.example.richgirls.adapter

import android.content.Context
import android.content.SharedPreferences

class MyPreferences(context: Context) {
    private var PRIVATE_MODE = 0
    private val PREF_NAME = "nome"
    val sharedPref: SharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE)

    fun salvarNome(nome: String){
        sharedPref.edit().putString(PREF_NAME, nome)
    }

    fun buscarNome(): String{
        return sharedPref.getString(PREF_NAME, "").toString()
    }
}
package com.example.richgirls.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitialize {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.myjson.com/bins/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun myService(): MyService = retrofit.create(MyService::class.java)

}
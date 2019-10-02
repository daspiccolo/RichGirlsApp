package com.example.richgirls.service

import com.example.richgirls.models.Contato
import com.example.richgirls.models.LoginResponse
import com.example.richgirls.models.TransferenciaConfirmacao
import retrofit2.Call
import retrofit2.http.GET

interface MyService {

    @GET("19u7u1")
    fun login() : Call<LoginResponse>

    @GET("https://techgirlsapp.herokuapp.com/api/contato")
    fun listarContatos() : Call<List<Contato>>

    @GET("yeo65")
    fun fazerTransferencia() : Call<TransferenciaConfirmacao>

}
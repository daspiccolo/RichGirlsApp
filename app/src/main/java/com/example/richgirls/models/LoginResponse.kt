package com.example.richgirls.models

import com.google.gson.annotations.SerializedName


class LoginResponse(
    @SerializedName("mensagem") val mensagem: String,
    @SerializedName("nome") val nome: String,
    @SerializedName("sessao") val sessao: String
)
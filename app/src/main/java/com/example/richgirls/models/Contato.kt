package com.example.richgirls.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Contato(
    @SerializedName("nome") val nome: String,
    @SerializedName("banco") val banco: String,
    @SerializedName("agencia") val agencia: String,
    @SerializedName("conta") val conta: String
) : Serializable {

}

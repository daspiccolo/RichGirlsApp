package com.example.richgirls.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

class TransferenciaConfirmacao(
    @SerializedName("mensagem") val mensagem :String,
    @SerializedName("id_comprovante") val comprovanteId :String,
    @SerializedName("recebedor") val recebedor : Contato,
    @SerializedName("valor_transferido") val valor: Double,
    @SerializedName("data_transferencia") val dataTransferencia: String
) : Serializable {

}
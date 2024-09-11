package com.devrhyan.eletriccarapp.models

data class Car (
    val id : Int,
    val preco : String,
    val bateria : String,
    val potencia : String,
    val recarga : String,
    val urlPhoto : String? = null
)

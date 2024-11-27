package com.example.plantasapi.models

data class Plant(
    val name: String,
    val wateringPeriod: Int, // Periodo en días
    var photoUri: String? = null // Ruta opcional de la foto
)

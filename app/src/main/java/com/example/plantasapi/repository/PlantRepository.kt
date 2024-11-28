package com.example.plantasapi.repository

import retrofit2.Call
import com.example.plantasapi.network.ApiClient
import com.example.plantasapi.network.ApiService
import okhttp3.ResponseBody

class PlantRepository {
    private val apiService: ApiService = ApiClient.retrofit.create(ApiService::class.java)

    // Modificar para aceptar una lista de Base64
    fun identifyPlantBase64(base64Images: List<String>, apiKey: String): Call<ResponseBody> {
        // Se pasa como lista correctamente
        return apiService.identifyPlantBase64(base64Images, apiKey)
    }

}


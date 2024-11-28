package com.example.plantasapi.repository

import com.example.plantasapi.network.ApiClient
import com.example.plantasapi.network.ApiService
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call

class PlantRepository {
    private val apiService: ApiService = ApiClient.retrofit.create(ApiService::class.java)

    fun identifyPlantBase64(apiKey: String, requestBody: RequestBody): Call<ResponseBody> {
        return apiService.identifyPlantBase64(apiKey, requestBody)  // Pasamos apiKey y requestBody a la llamada
    }
}

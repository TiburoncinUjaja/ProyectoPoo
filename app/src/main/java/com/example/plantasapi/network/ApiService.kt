package com.example.plantasapi.network

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {
    @Multipart
    @POST("identification")
    fun identifyPlantBase64(
        @Part("image") base64Image: List<String>,  // Esto está correcto
        @Part("api_key") apiKey: String
    ): Call<ResponseBody>
}


package com.moviles.examenmoviles.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// Cliente Retrofit listo para conectar con el backend real
object RetrofitClient {

    // Cuando exista el backend, solo cambiar esta URL
    private const val BASE_URL = "https://api.panini-support.com/v1/"

    val apiService: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}
package io.twotle.uimakercompose.repository.backend

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BackendService {
    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("http://10.0.2.2:3000/dev/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService: IBackendService by lazy {
        retrofit.create(IBackendService::class.java)
    }
}
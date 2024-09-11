package com.devrhyan.eletriccarapp.services

import com.devrhyan.eletriccarapp.models.Car
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("cars-api/cars.json")
    suspend fun getCars() : Response<List<Car>>

    companion object {

        private val retrofitService : RetrofitService by lazy {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://igorbag.github.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            retrofit.create(RetrofitService::class.java)
        }

        fun getInstance() : RetrofitService {
            return  retrofitService;
        }
    }

}
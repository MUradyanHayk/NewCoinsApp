package com.example.newcoinstatsapp.network

import com.example.newcoinstatsapp.database.Coin
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CoinApi {
//    @GET("?skip=0&limit=5&currency=EUR")
//    fun getAllCoins(): Call<Map<String, MutableList<Coin>>>

    @GET("?")
    fun getAllCoins(@Query("skip") skip: String, @Query("limit") limit: String, @Query("currency") currency: String): Call<Map<String, MutableList<Coin>>>
}
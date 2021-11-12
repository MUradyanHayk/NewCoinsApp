package com.example.newcoinstatsapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.newcoinstatsapp.database.Coin
import com.example.newcoinstatsapp.database.CoinDao
import com.example.newcoinstatsapp.database.CoinDatabase
import com.example.newcoinstatsapp.network.RetrofitInstance
import com.example.newcoinstatsapp.utils.AsyncUtils
import retrofit2.Call
import retrofit2.http.Query

class CoinRepository private constructor(context: Context) {
    fun getAllCoinsFromServer(skip: String = "0", limit: String = "10", currency: String = "EUR"): Call<Map<String, MutableList<Coin>>> {
        return RetrofitInstance.api.getAllCoins(skip, limit, currency)
    }

    fun getAllCoins(): LiveData<MutableList<Coin>>? {
        return coinDao?.getAllCoins()
    }

    fun getCoin(identifier: String): Coin? {
        return coinDao?.getCoin(identifier)
    }

    fun deleteCoin(coin: Coin) {
        AsyncUtils.executor.execute {
            coinDao?.delete(coin)
        }
    }

    fun getFavoriteCoins(): LiveData<MutableList<Coin>>? {
        return coinDao?.getFavoriteCoins()
    }

    fun updateCoin(coin: Coin) {
        AsyncUtils.executor.execute {
            coinDao?.update(coin)
        }
    }

    fun addCoin(coin: Coin) {
        AsyncUtils.executor.execute {
            coinDao?.insert(coin)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: CoinRepository? = null
        private val LOCK = Any()
        private var coinDao: CoinDao? = null

//        fun initialize(context: Context) {
//            if (INSTANCE == null) {
//                synchronized(LOCK) {
//                    if (INSTANCE == null) {
//                        INSTANCE = CoinRepository(context)
//                        coinDao = CoinDatabase(context).coinDao()
//                    }
//                }
//            }
//        }

        operator fun invoke(context: Context) = INSTANCE ?: synchronized(LOCK) {
            INSTANCE ?: CoinRepository(context).also {
                INSTANCE = it
                coinDao = CoinDatabase(context).coinDao()
            }
        }
    }
}
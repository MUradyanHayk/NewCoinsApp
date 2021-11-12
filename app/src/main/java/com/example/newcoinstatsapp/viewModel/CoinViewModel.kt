package com.example.newcoinstatsapp.viewModel

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newcoinstatsapp.database.Coin
import com.example.newcoinstatsapp.repository.CoinRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class CoinViewModel : ViewModel() {
    private var repository: CoinRepository? = null
    var isFavorite: MutableLiveData<Boolean> = MutableLiveData()

    fun initialize(context: Context) {
        repository = CoinRepository(context)
    }

    fun download(context: Context) {
        repository?.getAllCoinsFromServer()?.enqueue(object : Callback<Map<String, MutableList<Coin>>> {
            override fun onResponse(call: Call<Map<String, MutableList<Coin>>>, response: Response<Map<String, MutableList<Coin>>>) {
                val coins = response.body()?.get("coins")
                val size = coins?.size ?: 0
                for (i in 0 until size) {
                    val coin = coins?.get(i)
                    coin?.identifier = "${UUID.randomUUID()}_${System.currentTimeMillis()}_${coin?.hashCode()}"
                    if (coin != null) {
                        this@CoinViewModel.addCoin(coin)
                    }
                }
            }

            override fun onFailure(call: Call<Map<String, MutableList<Coin>>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    fun getAllCoins(): LiveData<MutableList<Coin>>? {
        return repository?.getAllCoins()
    }

    fun getFavoriteCoins(): LiveData<MutableList<Coin>>? {
        return repository?.getFavoriteCoins()
    }

    fun getCoin(identifier: String): Coin? {
        return repository?.getCoin(identifier)
    }

    fun deleteCoin(coin: Coin) {
        repository?.deleteCoin(coin)
    }

    fun updateCoin(coin: Coin) {
        repository?.updateCoin(coin)
    }

    fun addCoin(coin: Coin) {
        repository?.addCoin(coin)
    }

    fun changeFavorite(isFavorite: Boolean) {
        this.isFavorite.value = isFavorite
    }

    fun updateFavorite(identifier: String) {
        val coin = getCoin(identifier) ?: return

        val isFavorite = !(this.isFavorite.value ?: false)
        this.isFavorite.value = isFavorite
        coin.isFavorite = isFavorite

        updateCoin(coin)
    }
}
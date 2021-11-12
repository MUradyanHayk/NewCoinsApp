package com.example.newcoinstatsapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface CoinDao {
    @Query("SELECT * FROM _coin_table")
    fun getAllCoins(): LiveData<MutableList<Coin>>

    @Query("SELECT * FROM _coin_table WHERE _identifier=:identifier")
    fun getCoin(identifier: String): Coin

    @Query("SELECT * FROM _coin_table WHERE _isFavorite=1")
    fun getFavoriteCoins(): LiveData<MutableList<Coin>>

    @Insert(onConflict = REPLACE)
    fun insert(coin: Coin)

    @Update(onConflict = REPLACE)
    fun update(coin: Coin)

    @Delete
    fun delete(coin: Coin)
}
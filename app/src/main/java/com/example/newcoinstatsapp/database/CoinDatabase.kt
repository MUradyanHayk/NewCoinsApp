package com.example.newcoinstatsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newcoinstatsapp.utils.Constants

@Database(entities = [Coin::class], version = Constants.DATABASE_VERSION)
abstract class CoinDatabase : RoomDatabase() {

    abstract fun coinDao(): CoinDao

    companion object {
        @Volatile
        private var INSTANCE: CoinDatabase? = null
        private val LOCK = Any()

//        operator fun invoke(context: Context):CoinDatabase? {
//            if (INSTANCE == null) {
//                synchronized(LOCK) {
//                    if (INSTANCE == null) {
//                        INSTANCE = createDatabase(context)
//                    }
//                }
//            }
//            return INSTANCE
//        }

        operator fun invoke(context: Context) = INSTANCE?: synchronized(LOCK){
            INSTANCE?: createDatabase(context).also { INSTANCE = it }
        }

        private fun createDatabase(context: Context): CoinDatabase {
            return Room.databaseBuilder(context, CoinDatabase::class.java, Constants.DATABASE_NAME).allowMainThreadQueries().build()
        }
    }
}
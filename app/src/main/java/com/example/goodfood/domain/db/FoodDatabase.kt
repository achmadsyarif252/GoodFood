package com.example.goodfood.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.goodfood.data.FoodConverter
import com.example.goodfood.domain.dao.FoodDao
import com.example.goodfood.domain.dao.TransactionDao
import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.Transaction

@Database(entities = [Food::class, Transaction::class], version = 2, exportSchema = false)
@TypeConverters(FoodConverter::class)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
    abstract fun transactionDao(): TransactionDao

    companion object {
        @Volatile
        private var INSTANCE: FoodDatabase? = null

        fun getDatabase(context: Context): FoodDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    FoodDatabase::class.java,
                    "good_food_database"
                ).fallbackToDestructiveMigration().build()
                INSTANCE = instance
                instance
            }
        }
    }
}

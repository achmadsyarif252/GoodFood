package com.example.goodfood.core.domain.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.goodfood.core.data.FoodConverter
import com.example.goodfood.core.data.PaymentConverter
import com.example.goodfood.core.domain.dao.FoodDao
import com.example.goodfood.core.domain.dao.RestaurantDao
import com.example.goodfood.core.domain.dao.ReviewDao
import com.example.goodfood.core.domain.dao.TransactionDao
import com.example.goodfood.core.domain.dao.UserDao
import com.example.goodfood.core.domain.dao.WalletDao
import com.example.goodfood.core.domain.model.Food
import com.example.goodfood.core.domain.model.MyWallet
import com.example.goodfood.core.domain.model.Restaurant
import com.example.goodfood.core.domain.model.Review
import com.example.goodfood.core.domain.model.Transaction
import com.example.goodfood.core.domain.model.User

@Database(
    entities = [Food::class, Transaction::class, Restaurant::class, Review::class, MyWallet::class, User::class],
    version = 9,
    exportSchema = false
)
@TypeConverters(FoodConverter::class, PaymentConverter::class)
abstract class FoodDatabase : RoomDatabase() {
    abstract fun foodDao(): FoodDao
    abstract fun transactionDao(): TransactionDao
    abstract fun restaurantDao(): RestaurantDao
    abstract fun reviewDao(): ReviewDao
    abstract fun walletDao(): WalletDao
    abstract fun userDao(): UserDao

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

package com.example.goodfood.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.goodfood.core.data.FoodConverter
import com.example.goodfood.core.data.PaymentConverter
import com.example.goodfood.core.data.source.local.entity.FoodEntity
import com.example.goodfood.core.data.source.local.entity.MyWallet
import com.example.goodfood.core.data.source.local.entity.Restaurant
import com.example.goodfood.core.data.source.local.entity.ReviewEntity
import com.example.goodfood.core.data.source.local.entity.TransactionEntity
import com.example.goodfood.core.data.source.local.entity.UserEntity

@Database(
    entities = [FoodEntity::class, TransactionEntity::class, Restaurant::class, ReviewEntity::class, MyWallet::class, UserEntity::class],
    version = 10,
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

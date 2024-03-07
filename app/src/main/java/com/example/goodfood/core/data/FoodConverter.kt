package com.example.goodfood.core.data

import androidx.room.TypeConverter
import com.example.goodfood.core.data.source.local.entity.FoodEntity
import com.google.gson.Gson

class FoodConverter {
    @TypeConverter
    fun fromFood(foodEntity: FoodEntity): String {
        return Gson().toJson(foodEntity)
    }

    @TypeConverter
    fun toFood(foodString: String): FoodEntity {
        return Gson().fromJson(foodString, FoodEntity::class.java)
    }
}

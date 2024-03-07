package com.example.goodfood.core.data

import androidx.room.TypeConverter
import com.example.goodfood.core.domain.model.Food
import com.google.gson.Gson

class FoodConverter {
    @TypeConverter
    fun fromFood(food: Food): String {
        return Gson().toJson(food)
    }

    @TypeConverter
    fun toFood(foodString: String): Food {
        return Gson().fromJson(foodString, Food::class.java)
    }
}

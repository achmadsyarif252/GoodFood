package com.example.goodfood.core.data.source.local

import android.util.Log
import com.example.goodfood.core.data.source.local.entity.FoodEntity
import com.example.goodfood.core.data.source.local.room.FoodDao
import kotlinx.coroutines.flow.Flow

class FoodDataSource(private val foodDao: FoodDao) : IFoodDataSource {
    override suspend fun insert(foodEntity: FoodEntity) {
        foodDao.insert(foodEntity)
    }

    override suspend fun update(foodEntity: FoodEntity) {
        Log.d("SET FAVORITE DI DATA SOURCE", foodEntity.isFavorite.toString())
        foodDao.update(foodEntity)
    }

    override suspend fun delete(foodEntity: FoodEntity) {
        foodDao.delete(foodEntity)
    }

    override suspend fun isFoodListEmpty(): Boolean {
        return foodDao.getCount() == 0
    }

    override fun getAllFood(): Flow<List<FoodEntity>> {
        return foodDao.getAllFood()
    }

    override suspend fun insertAllFood(listFoodEntity: List<FoodEntity>) {
        foodDao.insertAllFoods(listFoodEntity)
    }

}
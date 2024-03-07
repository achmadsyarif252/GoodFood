package com.example.goodfood.core.utils

import com.example.goodfood.core.data.source.local.entity.FoodEntity
import com.example.goodfood.core.data.source.local.entity.TransactionEntity
import com.example.goodfood.core.domain.model.Food
import com.example.goodfood.core.domain.model.Transaction

object DataMapper {
    fun mapFoodEntitiesToDomain(input: List<FoodEntity>): List<Food> =
        input.map {
            Food(
                id = it.id,
                image = it.image,
                name = it.name,
                totalRestaurant = it.totalRestaurant,
                type = it.type,
                price = it.price,
                deskripsi = it.deskripsi,
                isFavorite = it.isFavorite,
            )
        }

    fun mapTransactionEntityToDomain(transaction: TransactionEntity): Transaction = Transaction(
        transaction.id, Food(
            id = transaction.id,
            image = transaction.foodEntity.image,
            name = transaction.foodEntity.name,
            totalRestaurant = transaction.foodEntity.totalRestaurant,
            type = transaction.foodEntity.type,
            price = transaction.foodEntity.price,
            deskripsi = transaction.foodEntity.deskripsi,
            isFavorite = transaction.foodEntity.isFavorite,
        ), transaction.total
    )

    fun mapTransactionEntitiesToDomain(input: List<TransactionEntity>): List<Transaction> =
        input.map {
            mapTransactionEntityToDomain(it)
        }

    fun mapFoodDomainToEntity(input: Food) = FoodEntity(
        id = input.id,
        image = input.image,
        name = input.name,
        totalRestaurant = input.totalRestaurant,
        type = input.type,
        price = input.price,
        deskripsi = input.deskripsi,
        isFavorite = input.isFavorite,
    )

    fun mapTransactionDomainToEntity(transaction: Transaction) = TransactionEntity(
        id = transaction.id,
        foodEntity = FoodEntity(
            image = transaction.food.image,
            name = transaction.food.name,
            totalRestaurant = transaction.food.totalRestaurant,
            type = transaction.food.type,
            price = transaction.food.price,
            deskripsi = transaction.food.deskripsi,
            isFavorite = transaction.food.isFavorite,
        ),
        total = transaction.total
    )
}
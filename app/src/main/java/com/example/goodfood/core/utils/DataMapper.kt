package com.example.goodfood.core.utils

import com.example.goodfood.core.data.source.local.entity.FoodEntity
import com.example.goodfood.core.data.source.local.entity.RestaurantEntity
import com.example.goodfood.core.data.source.local.entity.ReviewEntity
import com.example.goodfood.core.data.source.local.entity.TransactionEntity
import com.example.goodfood.core.data.source.local.entity.UserEntity
import com.example.goodfood.core.domain.model.Food
import com.example.goodfood.core.domain.model.Restaurant
import com.example.goodfood.core.domain.model.Review
import com.example.goodfood.core.domain.model.Transaction
import com.example.goodfood.core.domain.model.User

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

    private fun mapTransactionEntityToDomain(transaction: TransactionEntity): Transaction =
        Transaction(
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

    fun mapRestaurantEntitiesToDomain(input: List<RestaurantEntity>): List<Restaurant> = input.map {
        Restaurant(
            id = it.id,
            name = it.name,
            location = it.location,
            establishDate = it.establishDate,
            rating = it.rating,
            photo = it.photo,
            description = it.description,
            isFavorite = it.isFavorite
        )
    }

    fun mapFoodEntityToDomain(input: FoodEntity) = Food(
        id = input.id,
        image = input.image,
        name = input.name,
        totalRestaurant = input.totalRestaurant,
        type = input.type,
        price = input.price,
        deskripsi = input.deskripsi,
        isFavorite = input.isFavorite,
    )

    fun mapReviewEntitiesToDomain(input: List<ReviewEntity>) = input.map {
        it.photo?.let { it1 ->
            Review(
                id = it.id,
                name = it.name,
                photo = it1,
                rating = it.rating,
                review = it.review,
                food = mapFoodEntityToDomain(it.foodEntity)
            )
        }
    }

    fun mapUserEntitiesToDomain(input: List<User>) = input.map {
        User(
            id = it.id,
            email = it.email,
            password = it.password,
            phoneNumber = it.phoneNumber,
            image = it.image
        )
    }

    fun mapUserEntityToDomain(user: UserEntity?) =
        user?.let {
            User(
                id = it.id,
                email = user.email,
                password = user.password,
                phoneNumber = user.phoneNumber,
                image = user.image
            )
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

    fun mapRestaurantDomainToEntity(restaurant: Restaurant) = RestaurantEntity(
        id = restaurant.id,
        name = restaurant.name,
        location = restaurant.location,
        establishDate = restaurant.establishDate,
        rating = restaurant.rating,
        photo = restaurant.photo,
        description = restaurant.description,
        isFavorite = restaurant.isFavorite
    )

    fun mapReviewDomainToEntity(review: Review) = ReviewEntity(
        id = review.id,
        name = review.name,
        photo = review.photo,
        rating = review.rating,
        review = review.review,
        foodEntity = mapFoodDomainToEntity(review.food)
    )

    fun mapUserDomainToEntity(user: User) = UserEntity(
        id = user.id,
        email = user.email,
        password = user.password,
        phoneNumber = user.phoneNumber,
        image = user.image
    )

}
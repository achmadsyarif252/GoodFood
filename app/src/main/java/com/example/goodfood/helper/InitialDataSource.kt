package com.example.goodfood.helper

import com.example.goodfood.R
import com.example.goodfood.domain.model.Food

object InitialDataSource {
    fun getFood(): List<Food> {
        return listOf(
            Food(image = R.drawable.hotdog, name = "Hot Dog", totalRestaurant = 120, price = 32.0),
            Food(image = R.drawable.hotpot, name = "Hot Pot", totalRestaurant = 32, price = 24.0),
            Food(image = R.drawable.ramen, name = "Ramen", totalRestaurant = 54, price = 54.0),
            Food(
                image = R.drawable.roastchicken,
                name = "Roast Chicken",
                totalRestaurant = 122,
                price = 45.0
            ),
            Food(
                image = R.drawable.bibimbap,
                name = "Bibimbap",
                totalRestaurant = 90,
                price = 32.0
            ),
            Food(
                image = R.drawable.hotdog,
                name = "Hot Dog 2",
                totalRestaurant = 120,
                price = 10.0
            ),
            Food(image = R.drawable.hotpot, name = "Hot Pot 2", totalRestaurant = 32, price = 22.0),
            Food(image = R.drawable.ramen, name = "Ramen 2", totalRestaurant = 54, price = 39.0),
            Food(
                image = R.drawable.roastchicken,
                name = "Roast Chicken 2",
                totalRestaurant = 122,
                price = 78.0
            ),
            Food(
                image = R.drawable.bibimbap,
                name = "Bibimbap 2",
                totalRestaurant = 90,
                price = 32.0
            ),
            Food(
                image = R.drawable.hotdog,
                name = "Hot Dog 3",
                totalRestaurant = 120,
                price = 88.0
            ),
            Food(image = R.drawable.hotpot, name = "Hot Pot 3", totalRestaurant = 32, price = 65.0),
            Food(image = R.drawable.ramen, name = "Ramen 3", totalRestaurant = 54, price = 32.0),
            Food(image = R.drawable.roastchicken, name = "Roast Chicken 3", totalRestaurant = 122),
            Food(image = R.drawable.bibimbap, name = "Bibimbap 3", totalRestaurant = 90),
        )
    }
}
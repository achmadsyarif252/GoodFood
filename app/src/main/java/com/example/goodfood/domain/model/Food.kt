package com.example.goodfood.domain.model

import com.example.goodfood.R


data class Food(
    val image: Int,
    val name: String,
    val totalRestaurant: Int,
    val type: String = "Dishes",
    val price: Double = 10.0
)

val listFood = listOf(
    Food(image = R.drawable.hotdog, name = "Hot Dog", 120),
    Food(image = R.drawable.hotpot, name = "Hot Pot", 32),
    Food(image = R.drawable.ramen, name = "Ramen", 54),
    Food(image = R.drawable.roastchicken, name = "Roast Chicken", 122),
    Food(image = R.drawable.bibimbap, name = "Bibimbap", 90),
    Food(image = R.drawable.hotdog, name = "Hot Dog", 120),
    Food(image = R.drawable.hotpot, name = "Hot Pot", 32),
    Food(image = R.drawable.ramen, name = "Ramen", 54),
    Food(image = R.drawable.roastchicken, name = "Roast Chicken", 122),
    Food(image = R.drawable.bibimbap, name = "Bibimbap", 90), Food(image = R.drawable.hotdog, name = "Hot Dog", 120),
    Food(image = R.drawable.hotpot, name = "Hot Pot", 32),
    Food(image = R.drawable.ramen, name = "Ramen", 54),
    Food(image = R.drawable.roastchicken, name = "Roast Chicken", 122),
    Food(image = R.drawable.bibimbap, name = "Bibimbap", 90),
)
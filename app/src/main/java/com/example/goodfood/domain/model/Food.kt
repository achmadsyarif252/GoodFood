package com.example.goodfood.domain.model

import com.example.goodfood.R


data class Food(
    val image: Int,
    val name: String,
    val totalRestaurant: Int,
    val type: String = "Dishes",
    val price: Double = 10.0,
    val deskripsi: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis nisl vitae leo consequat ultrices. Quisque auctor, nunc quis tincidunt sagittis, justo erat luctus nisi, id aliquet augue lorem quis sapien. Morbi euismod, lacus in consequat malesuada, eros nisl rhoncus massa, quis varius leo elit quis magna. Fusce vitae nisi quis nisl tincidunt ullamcorper. Phasellus quis lorem id leo tincidunt finibus. Ut quis lorem sit amet leo tincidunt blandit. Mauris quis nisl quis lorem tincidunt tincidunt. Donec quis nisl quis lorem tincidunt tincidunt."
)

data class Transaction(
    val id: Int,
    var food: Food,
    var total: Int
)

val listFood = listOf(
    Food(image = R.drawable.hotdog, name = "Hot Dog", 120, price = 32.0),
    Food(image = R.drawable.hotpot, name = "Hot Pot", 32, price = 24.0),
    Food(image = R.drawable.ramen, name = "Ramen", 54, price = 54.0),
    Food(image = R.drawable.roastchicken, name = "Roast Chicken", 122, price = 45.0),
    Food(image = R.drawable.bibimbap, name = "Bibimbap", 90, price = 32.0),
    Food(image = R.drawable.hotdog, name = "Hot Dog 2", 120, price = 10.0),
    Food(image = R.drawable.hotpot, name = "Hot Pot 2", 32, price = 22.0),
    Food(image = R.drawable.ramen, name = "Ramen 2", 54, price = 39.0),
    Food(image = R.drawable.roastchicken, name = "Roast Chicken 2", 122, price = 78.0),
    Food(image = R.drawable.bibimbap, name = "Bibimbap 2", 90, price = 32.0),
    Food(image = R.drawable.hotdog, name = "Hot Dog 3", 120, price = 88.0),
    Food(image = R.drawable.hotpot, name = "Hot Pot 3", 32, price = 65.0),
    Food(image = R.drawable.ramen, name = "Ramen 3", 54, price = 32.0),
    Food(image = R.drawable.roastchicken, name = "Roast Chicken 3", 122),
    Food(image = R.drawable.bibimbap, name = "Bibimbap 3", 90),
)
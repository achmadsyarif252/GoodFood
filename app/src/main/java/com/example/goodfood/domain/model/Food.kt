package com.example.goodfood.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.goodfood.R


@Entity(tableName = "food_table")
data class Food(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val image: Int,
    val name: String,
    val totalRestaurant: Int,
    val type: String = "Dishes",
    val price: Double = 10.0,
    val deskripsi: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis nisl vitae leo consequat ultrices. Quisque auctor, nunc quis tincidunt sagittis, justo erat luctus nisi, id aliquet augue lorem quis sapien. Morbi euismod, lacus in consequat malesuada, eros nisl rhoncus massa, quis varius leo elit quis magna. Fusce vitae nisi quis nisl tincidunt ullamcorper. Phasellus quis lorem id leo tincidunt finibus. Ut quis lorem sit amet leo tincidunt blandit. Mauris quis nisl quis lorem tincidunt tincidunt. Donec quis nisl quis lorem tincidunt tincidunt."
)



val listFood = listOf(
    Food(image = R.drawable.hotdog, name = "Hot Dog", totalRestaurant = 120, price = 32.0),
    Food(image = R.drawable.hotpot, name = "Hot Pot", totalRestaurant = 32, price = 24.0),
    Food(image = R.drawable.ramen, name = "Ramen", totalRestaurant = 54, price = 54.0),
    Food(
        image = R.drawable.roastchicken,
        name = "Roast Chicken",
        totalRestaurant = 122,
        price = 45.0
    ),
    Food(image = R.drawable.bibimbap, name = "Bibimbap", totalRestaurant = 90, price = 32.0),
    Food(image = R.drawable.hotdog, name = "Hot Dog 2", totalRestaurant = 120, price = 10.0),
    Food(image = R.drawable.hotpot, name = "Hot Pot 2", totalRestaurant = 32, price = 22.0),
    Food(image = R.drawable.ramen, name = "Ramen 2", totalRestaurant = 54, price = 39.0),
    Food(
        image = R.drawable.roastchicken,
        name = "Roast Chicken 2",
        totalRestaurant = 122,
        price = 78.0
    ),
    Food(image = R.drawable.bibimbap, name = "Bibimbap 2", totalRestaurant = 90, price = 32.0),
    Food(image = R.drawable.hotdog, name = "Hot Dog 3", totalRestaurant = 120, price = 88.0),
    Food(image = R.drawable.hotpot, name = "Hot Pot 3", totalRestaurant = 32, price = 65.0),
    Food(image = R.drawable.ramen, name = "Ramen 3", totalRestaurant = 54, price = 32.0),
    Food(image = R.drawable.roastchicken, name = "Roast Chicken 3", totalRestaurant = 122),
    Food(image = R.drawable.bibimbap, name = "Bibimbap 3", totalRestaurant = 90),
)
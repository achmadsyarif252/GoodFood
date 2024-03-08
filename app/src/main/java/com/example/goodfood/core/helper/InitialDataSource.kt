package com.example.goodfood.core.helper

import com.example.goodfood.R
import com.example.goodfood.core.data.source.local.entity.FoodEntity
import com.example.goodfood.core.data.source.local.entity.MyWallet
import com.example.goodfood.core.data.source.local.entity.RestaurantEntity
import com.example.goodfood.core.data.source.local.entity.listPaymentMethod

object InitialDataSource {
    fun getFood(): List<FoodEntity> {
        return listOf(
            FoodEntity(image = R.drawable.hotdog, name = "Hot Dog", totalRestaurant = 120, price = 32.0),
            FoodEntity(image = R.drawable.hotpot, name = "Hot Pot", totalRestaurant = 32, price = 24.0),
            FoodEntity(image = R.drawable.ramen, name = "Ramen", totalRestaurant = 54, price = 54.0),
            FoodEntity(
                image = R.drawable.roastchicken,
                name = "Roast Chicken",
                totalRestaurant = 122,
                price = 45.0
            ),
            FoodEntity(
                image = R.drawable.bibimbap,
                name = "Bibimbap",
                totalRestaurant = 90,
                price = 32.0
            ),
            FoodEntity(
                image = R.drawable.hotdog,
                name = "Hot Dog 2",
                totalRestaurant = 120,
                price = 10.0
            ),
            FoodEntity(image = R.drawable.hotpot, name = "Hot Pot 2", totalRestaurant = 32, price = 22.0),
            FoodEntity(image = R.drawable.ramen, name = "Ramen 2", totalRestaurant = 54, price = 39.0),
            FoodEntity(
                image = R.drawable.roastchicken,
                name = "Roast Chicken 2",
                totalRestaurant = 122,
                price = 78.0
            ),
            FoodEntity(
                image = R.drawable.bibimbap,
                name = "Bibimbap 2",
                totalRestaurant = 90,
                price = 32.0
            ),
            FoodEntity(
                image = R.drawable.hotdog,
                name = "Hot Dog 3",
                totalRestaurant = 120,
                price = 88.0
            ),
            FoodEntity(image = R.drawable.hotpot, name = "Hot Pot 3", totalRestaurant = 32, price = 65.0),
            FoodEntity(image = R.drawable.ramen, name = "Ramen 3", totalRestaurant = 54, price = 32.0),
            FoodEntity(image = R.drawable.roastchicken, name = "Roast Chicken 3", totalRestaurant = 122),
            FoodEntity(image = R.drawable.bibimbap, name = "Bibimbap 3", totalRestaurant = 90),
        )
    }

    fun getRestaurants(): List<RestaurantEntity> {
        return listOf(
            RestaurantEntity(
                0,
                "Bakso Malang",
                "Jakarta",
                1995,
                "4.5",
                R.drawable.resto1,
                isFavorite = false
            ),
            RestaurantEntity(0, "Soto Betawi", "Bogor", 2000, "4.2", R.drawable.resto2),
            RestaurantEntity(1, "Nasi Padang", "Bandung", 1987, "4.7", R.drawable.resto3),
            RestaurantEntity(2, "Sate Ayam", "Yogyakarta", 1992, "4.3", R.drawable.resto4),
            RestaurantEntity(3, "Gado-Gado", "Surabaya", 2005, "4.1", R.drawable.resto1),
            RestaurantEntity(4, "Rendang", "Medan", 1978, "4.8", R.drawable.resto2),
            RestaurantEntity(5, "Siomay", "Semarang", 2003, "4.0", R.drawable.resto3),
            RestaurantEntity(6, "Pempek", "Palembang", 1985, "4.4", R.drawable.resto4),
            RestaurantEntity(7, "Ayam Taliwang", "Lombok", 1998, "4.6", R.drawable.resto1),
            RestaurantEntity(8, "Babi Guling", "Bali", 2001, "4.2", R.drawable.resto2),
            RestaurantEntity(9, "Bakso Malang", "Jakarta", 1995, "4.5", R.drawable.resto1),
            RestaurantEntity(10, "Soto Betawi", "Bogor", 2000, "4.2", R.drawable.resto2),
            RestaurantEntity(11, "Nasi Padang", "Bandung", 1987, "4.7", R.drawable.resto3),
            RestaurantEntity(12, "Sate Ayam", "Yogyakarta", 1992, "4.3", R.drawable.resto4),
            RestaurantEntity(13, "Gado-Gado", "Surabaya", 2005, "4.1", R.drawable.resto1),
            RestaurantEntity(14, "Rendang", "Medan", 1978, "4.8", R.drawable.resto2),
            RestaurantEntity(15, "Siomay", "Semarang", 2003, "4.0", R.drawable.resto3),
            RestaurantEntity(16, "Pempek", "Palembang", 1985, "4.4", R.drawable.resto4),
            RestaurantEntity(17, "Ayam Taliwang", "Lombok", 1998, "4.6", R.drawable.resto1),
            RestaurantEntity(18, "Babi Guling", "Bali", 2001, "4.2", R.drawable.resto2)
        )
    }

    fun getAllWallet(): List<MyWallet> {
        return listOf(
            MyWallet(id = 0, wallet = listPaymentMethod[0], 1000.0),
            MyWallet(id = 0, wallet = listPaymentMethod[1], 1000.0),
            MyWallet(id = 0, wallet = listPaymentMethod[2], 1000.0),
            MyWallet(id = 0, wallet = listPaymentMethod[3], 1000.0),
        )
    }
}
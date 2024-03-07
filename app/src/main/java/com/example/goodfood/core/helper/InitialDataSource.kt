package com.example.goodfood.core.helper

import com.example.goodfood.R
import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.domain.model.PaymentMethod
import com.example.goodfood.domain.model.Restaurant
import com.example.goodfood.domain.model.listPaymentMethod

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

    fun getRestaurants(): List<Restaurant> {
        return listOf(
            Restaurant(
                0,
                "Bakso Malang",
                "Jakarta",
                1995,
                "4.5",
                R.drawable.resto1,
                isFavorite = false
            ),
            Restaurant(0, "Soto Betawi", "Bogor", 2000, "4.2", R.drawable.resto2),
            Restaurant(1, "Nasi Padang", "Bandung", 1987, "4.7", R.drawable.resto3),
            Restaurant(2, "Sate Ayam", "Yogyakarta", 1992, "4.3", R.drawable.resto4),
            Restaurant(3, "Gado-Gado", "Surabaya", 2005, "4.1", R.drawable.resto1),
            Restaurant(4, "Rendang", "Medan", 1978, "4.8", R.drawable.resto2),
            Restaurant(5, "Siomay", "Semarang", 2003, "4.0", R.drawable.resto3),
            Restaurant(6, "Pempek", "Palembang", 1985, "4.4", R.drawable.resto4),
            Restaurant(7, "Ayam Taliwang", "Lombok", 1998, "4.6", R.drawable.resto1),
            Restaurant(8, "Babi Guling", "Bali", 2001, "4.2", R.drawable.resto2),
            Restaurant(9, "Bakso Malang", "Jakarta", 1995, "4.5", R.drawable.resto1),
            Restaurant(10, "Soto Betawi", "Bogor", 2000, "4.2", R.drawable.resto2),
            Restaurant(11, "Nasi Padang", "Bandung", 1987, "4.7", R.drawable.resto3),
            Restaurant(12, "Sate Ayam", "Yogyakarta", 1992, "4.3", R.drawable.resto4),
            Restaurant(13, "Gado-Gado", "Surabaya", 2005, "4.1", R.drawable.resto1),
            Restaurant(14, "Rendang", "Medan", 1978, "4.8", R.drawable.resto2),
            Restaurant(15, "Siomay", "Semarang", 2003, "4.0", R.drawable.resto3),
            Restaurant(16, "Pempek", "Palembang", 1985, "4.4", R.drawable.resto4),
            Restaurant(17, "Ayam Taliwang", "Lombok", 1998, "4.6", R.drawable.resto1),
            Restaurant(18, "Babi Guling", "Bali", 2001, "4.2", R.drawable.resto2)
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
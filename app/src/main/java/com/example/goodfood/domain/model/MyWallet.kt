package com.example.goodfood.domain.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.goodfood.R

@Entity(tableName = "table_wallet")
data class MyWallet(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val wallet: PaymentMethod,
    val totalSaldo: Double,
)

data class PaymentMethod(
    val name: String,
    @DrawableRes val image: Int
)


val listPaymentMethod = listOf(
    PaymentMethod("Mastercard", R.drawable.logo),
    PaymentMethod("Visa", R.drawable.visa),
    PaymentMethod("Paypal", R.drawable.paypal),
    PaymentMethod("Credit Card", R.drawable.credit),
)

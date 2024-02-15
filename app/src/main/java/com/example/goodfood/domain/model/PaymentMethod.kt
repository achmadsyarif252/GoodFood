package com.example.goodfood.domain.model

import androidx.annotation.DrawableRes
import com.example.goodfood.R

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
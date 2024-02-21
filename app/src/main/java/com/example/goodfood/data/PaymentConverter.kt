package com.example.goodfood.data

import androidx.room.TypeConverter
import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.domain.model.PaymentMethod
import com.google.gson.Gson

class PaymentConverter {
    @TypeConverter
    fun fromPayment(payment: PaymentMethod): String {
        return Gson().toJson(payment)
    }

    @TypeConverter
    fun toPayment(paymentString: String): PaymentMethod {
        return Gson().fromJson(paymentString, PaymentMethod::class.java)
    }
}
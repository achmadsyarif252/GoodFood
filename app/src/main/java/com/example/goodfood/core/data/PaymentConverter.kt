package com.example.goodfood.core.data

import androidx.room.TypeConverter
import com.example.goodfood.core.data.source.local.entity.PaymentMethod
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
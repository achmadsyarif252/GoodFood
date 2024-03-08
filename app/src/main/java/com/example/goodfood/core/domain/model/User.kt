package com.example.goodfood.core.domain.model


data class User(
    val id: Int = 0,
    val email: String="",
    val password: String="",
    val phoneNumber: String = "0895384252730",
    val image: ByteArray? = null

)

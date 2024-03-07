package com.example.goodfood.core.domain.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.goodfood.R

@Entity(tableName = "table_review")
data class Review(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @DrawableRes val photo: Int,
    val rating: Int,
    val review: String,
    val food: Food
)

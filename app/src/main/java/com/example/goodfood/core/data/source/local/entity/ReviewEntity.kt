package com.example.goodfood.core.data.source.local.entity

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_review")
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    @DrawableRes val photo: Int,
    val rating: Int,
    val review: String,
    val foodEntity: FoodEntity
)

package com.example.goodfood.core.domain.model

import androidx.annotation.DrawableRes

data class Restaurant(
    val id: Int = 0,
    val name: String,
    val location: String,
    val establishDate: Int,
    val rating: String,
    @DrawableRes val photo: Int,
    val description: String = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed quis leo quis nisi malesuada tincidunt. Fusce vitae nisl id augue ullamcorper consequat. Morbi auctor, lorem quis consequat sollicitudin, massa nisi sagittis urna, quis ultrices leo elit quis justo. Vivamus quis nisl eget nisl lacinia aliquet. Curabitur quis nunc id lectus tincidunt luctus. Mauris quis sapien ut erat sagittis rutrum. Nunc quis lorem vitae leo faucibus interdum.\n" +
            "\n" +
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus quis lacus quis eros aliquet tincidunt. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Suspendisse potenti. Cras quis magna id nisl blandit ullamcorper. Quisque id nisl quis augue luctus consequat. Donec quis nisl quis lorem sagittis vulputate. Sed quis nisl quis lorem sagittis vulputate.",
    val isFavorite: Boolean = false
)


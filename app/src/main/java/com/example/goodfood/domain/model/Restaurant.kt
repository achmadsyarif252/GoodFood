package com.example.goodfood.domain.model

import androidx.annotation.DrawableRes
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.goodfood.R

@Entity("table_restaurant")
data class Restaurant(
    @PrimaryKey(autoGenerate = true)
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

val restaurants = listOf(
    Restaurant(0, "Bakso Malang", "Jakarta", 1995, "4.5", R.drawable.resto1, isFavorite = false),
//    Restaurant("Soto Betawi", "Bogor", 2000, "4.2", R.drawable.resto2),
//    Restaurant("Nasi Padang", "Bandung", 1987, "4.7", R.drawable.resto3),
//    Restaurant("Sate Ayam", "Yogyakarta", 1992, "4.3", R.drawable.resto4),
//    Restaurant("Gado-Gado", "Surabaya", 2005, "4.1", R.drawable.resto1),
//    Restaurant("Rendang", "Medan", 1978, "4.8", R.drawable.resto2),
//    Restaurant("Siomay", "Semarang", 2003, "4.0", R.drawable.resto3),
//    Restaurant("Pempek", "Palembang", 1985, "4.4", R.drawable.resto4),
//    Restaurant("Ayam Taliwang", "Lombok", 1998, "4.6", R.drawable.resto1),
//    Restaurant("Babi Guling", "Bali", 2001, "4.2", R.drawable.resto2),
//    Restaurant("Bakso Malang", "Jakarta", 1995, "4.5", R.drawable.resto1),
//    Restaurant("Soto Betawi", "Bogor", 2000, "4.2", R.drawable.resto2),
//    Restaurant("Nasi Padang", "Bandung", 1987, "4.7", R.drawable.resto3),
//    Restaurant("Sate Ayam", "Yogyakarta", 1992, "4.3", R.drawable.resto4),
//    Restaurant("Gado-Gado", "Surabaya", 2005, "4.1", R.drawable.resto1),
//    Restaurant("Rendang", "Medan", 1978, "4.8", R.drawable.resto2),
//    Restaurant("Siomay", "Semarang", 2003, "4.0", R.drawable.resto3),
//    Restaurant("Pempek", "Palembang", 1985, "4.4", R.drawable.resto4),
//    Restaurant("Ayam Taliwang", "Lombok", 1998, "4.6", R.drawable.resto1),
//    Restaurant("Babi Guling", "Bali", 2001, "4.2", R.drawable.resto2)
)
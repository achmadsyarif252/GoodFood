package com.example.goodfood.domain.model

import androidx.annotation.DrawableRes
import com.example.goodfood.R

data class Restaurant(
    val name: String,
    val location: String,
    val establishDate: Int,
    val rating: String,
    @DrawableRes val photo: Int
)

val restaurants = listOf(
    Restaurant("Bakso Malang", "Jakarta", 1995, "4.5", R.drawable.resto1),
    Restaurant("Soto Betawi", "Bogor", 2000, "4.2", R.drawable.resto2),
    Restaurant("Nasi Padang", "Bandung", 1987, "4.7", R.drawable.resto3),
    Restaurant("Sate Ayam", "Yogyakarta", 1992, "4.3", R.drawable.resto4),
    Restaurant("Gado-Gado", "Surabaya", 2005, "4.1", R.drawable.resto1),
    Restaurant("Rendang", "Medan", 1978, "4.8", R.drawable.resto2),
    Restaurant("Siomay", "Semarang", 2003, "4.0", R.drawable.resto3),
    Restaurant("Pempek", "Palembang", 1985, "4.4", R.drawable.resto4),
    Restaurant("Ayam Taliwang", "Lombok", 1998, "4.6", R.drawable.resto1),
    Restaurant("Babi Guling", "Bali", 2001, "4.2", R.drawable.resto2),
    Restaurant("Bakso Malang", "Jakarta", 1995, "4.5", R.drawable.resto1),
    Restaurant("Soto Betawi", "Bogor", 2000, "4.2", R.drawable.resto2),
    Restaurant("Nasi Padang", "Bandung", 1987, "4.7", R.drawable.resto3),
    Restaurant("Sate Ayam", "Yogyakarta", 1992, "4.3", R.drawable.resto4),
    Restaurant("Gado-Gado", "Surabaya", 2005, "4.1", R.drawable.resto1),
    Restaurant("Rendang", "Medan", 1978, "4.8", R.drawable.resto2),
    Restaurant("Siomay", "Semarang", 2003, "4.0", R.drawable.resto3),
    Restaurant("Pempek", "Palembang", 1985, "4.4", R.drawable.resto4),
    Restaurant("Ayam Taliwang", "Lombok", 1998, "4.6", R.drawable.resto1),
    Restaurant("Babi Guling", "Bali", 2001, "4.2", R.drawable.resto2)
)
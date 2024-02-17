package com.example.goodfood.domain.model

import androidx.annotation.DrawableRes
import com.example.goodfood.R

data class Review(
    val name: String,
    @DrawableRes val photo: Int,
    val rating: Int,
    val review: String,
    val food: Food
)

// Membuat list dari 25 data Review
//val reviews = listOf(
//    Review(
//        "Nasi Goreng",
//        R.drawable.cita2,
//        5,
//        "Enak sekali, nasi gorengnya gurih dan pedas. Porsinya juga pas.",
//        listFood[0]
//    ),
//    Review(
//        "Soto Betawi",
//        R.drawable.cita2,
//        4,
//        "Soto betawinya mantap, kuahnya kental dan dagingnya empuk. Sayangnya, agak terlalu asin untuk selera saya.",
//        listFood[1]
//    ),
//    Review(
//        "Martabak Manis",
//        R.drawable.cita2,
//        5,
//        "Martabak manisnya lezat, banyak pilihan toppingnya. Saya suka yang coklat keju. Rasanya manis dan gurih.",
//        listFood[2]
//    ),
//    Review(
//        "Bakso Malang",
//        R.drawable.cita2,
//        3,
//        "Bakso malangnya lumayan, baksonya kenyal dan kuahnya segar. Tapi, sayurannya kurang banyak dan mie kuningnya terlalu lembek.",
//        listFood[3]
//    ),
//    Review(
//        "Gado-Gado",
//        R.drawable.cita2,
//        4,
//        "Gado-gadonya sehat dan enak, sayurannya segar dan bumbunya pas. Cocok untuk yang suka makanan ringan dan bergizi.",
//        listFood[4]
//    ),
//    Review(
//        "Rendang",
//        R.drawable.cita2,
//        5,
//        "Rendangnya juara, dagingnya empuk dan bumbunya meresap. Pedasnya juga pas, tidak terlalu pedas tapi juga tidak hambar.",
//        listFood[5]
//    ),
//    Review(
//        "Pempek Palembang",
//        R.drawable.cita2,
//        4,
//        "Pempek palembangnya enak, pempeknya kenyal dan cuka nya asam manis. Saya suka yang ada telurnya. Tapi, agak mahal harganya.",
//        listFood[6]
//    ),
//    Review(
//        "Sate Ayam",
//        R.drawable.cita2,
//        5,
//        "Sate ayamnya lezat, ayamnya empuk dan bumbu kacangnya gurih. Sambalnya juga pedas dan nikmat. Porsinya juga banyak.",
//        listFood[7]
//    ),
//    Review(
//        "Es Cendol",
//        R.drawable.cita2,
//        3,
//        "Es cendolnya segar, cendolnya kenyal dan santannya manis. Tapi, gula merahnya kurang banyak dan esnya terlalu encer.",
//        listFood[8]
//    ),
//)

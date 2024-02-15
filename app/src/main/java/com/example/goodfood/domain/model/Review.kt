package com.example.goodfood.domain.model

import androidx.annotation.DrawableRes
import com.example.goodfood.R

data class Review(
    val name: String,
    @DrawableRes val photo: Int,
    val rating: Int,
    val review: String
)

// Membuat list dari 25 data Review
val reviews = listOf(
    Review("Nasi Goreng", R.drawable.cita2, 5, "Enak sekali, nasi gorengnya gurih dan pedas. Porsinya juga pas."),
    Review("Soto Betawi", R.drawable.cita2, 4, "Soto betawinya mantap, kuahnya kental dan dagingnya empuk. Sayangnya, agak terlalu asin untuk selera saya."),
    Review("Martabak Manis", R.drawable.cita2, 5, "Martabak manisnya lezat, banyak pilihan toppingnya. Saya suka yang coklat keju. Rasanya manis dan gurih."),
    Review("Bakso Malang", R.drawable.cita2, 3, "Bakso malangnya lumayan, baksonya kenyal dan kuahnya segar. Tapi, sayurannya kurang banyak dan mie kuningnya terlalu lembek."),
    Review("Gado-Gado", R.drawable.cita2, 4, "Gado-gadonya sehat dan enak, sayurannya segar dan bumbunya pas. Cocok untuk yang suka makanan ringan dan bergizi."),
    Review("Rendang", R.drawable.cita2, 5, "Rendangnya juara, dagingnya empuk dan bumbunya meresap. Pedasnya juga pas, tidak terlalu pedas tapi juga tidak hambar."),
    Review("Pempek Palembang", R.drawable.cita2, 4, "Pempek palembangnya enak, pempeknya kenyal dan cuka nya asam manis. Saya suka yang ada telurnya. Tapi, agak mahal harganya."),
    Review("Sate Ayam", R.drawable.cita2, 5, "Sate ayamnya lezat, ayamnya empuk dan bumbu kacangnya gurih. Sambalnya juga pedas dan nikmat. Porsinya juga banyak."),
    Review("Es Cendol", R.drawable.cita2, 3, "Es cendolnya segar, cendolnya kenyal dan santannya manis. Tapi, gula merahnya kurang banyak dan esnya terlalu encer."),
    Review("Mie Ayam", R.drawable.cita2, 4, "Mie ayamnya enak, mie nya kenyal dan ayamnya gurih. Kuahnya juga hangat dan sedap. Tapi, sayurannya kurang segar dan kecapnya terlalu manis."),
    Review("Kerak Telor", R.drawable.cita2, 4, "Kerak telornya unik, telornya renyah dan berasnya pulen. Toppingnya juga banyak, ada serundeng, bawang goreng, dan ebi. Tapi, agak berminyak dan panas."),
    Review("Siomay Bandung", R.drawable.cita2, 5, "Siomay bandungnya mantap, siomaynya kenyal dan saus kacangnya gurih. Saya suka yang ada tahu dan telurnya. Rasanya juga pas, tidak terlalu asin atau manis."),
    Review("Nasi Uduk", R.drawable.cita2, 4, "Nasi uduknya enak, nasinya wangi dan lauknya banyak. Saya suka yang ada ayam goreng dan tempe oreknya. Tapi, sambalnya terlalu pedas dan kuahnya terlalu encer."),
    Review("Lontong Sayur", R.drawable.cita2, 3, "Lontong sayurnya lumayan, lontongnya pulen dan sayurnya segar. Tapi, kuahnya terlalu kental dan santannya terlalu berat."),
    Review("Es Teler", R.drawable.cita2, 5, "Es telernya segar, buahnya manis dan esnya dingin. Saya suka yang ada alpukat, kelapa muda, dan nangkanya. Santannya juga pas, tidak terlalu manis atau encer."),
    Review("Bubur Ayam", R.drawable.cita2, 4, "Bubur ayamnya enak, buburnya lembut dan ayamnya gurih. Toppingnya juga banyak, ada kerupuk, bawang goreng, dan cakwe. Tapi, agak terlalu panas dan asin."),
    Review("Sop Buntut", R.drawable.cita2, 5, "Sop buntutnya lezat, buntutnya empuk dan kuahnya gurih. Saya suka yang ada wortel dan kentangnya. Rasanya juga pas, tidak terlalu asin atau hambar."),
    Review("Pisang Goreng", R.drawable.cita2, 3, "Pisang gorengnya lumayan, pisangnya manis dan gorengannya renyah. Tapi, agak terlalu berminyak dan gosong."),
    Review("Nasi Kuning", R.drawable.cita2, 4, "Nasi kuningnya enak, nasinya wangi dan kuningnya merata. Lauknya juga banyak, ada telur, ayam, dan tempe. Tapi, sambalnya kurang pedas dan sayurnya kurang segar."),
    Review("Es Campur", R.drawable.cita2, 4, "Es campurnya segar, campurannya banyak dan esnya dingin. Saya suka yang ada cincau, kolang kaling, dan selasihnya. Sirupnya juga pas, tidak terlalu manis atau encer."),
    Review("Rawon", R.drawable.cita2, 5, "Rawonnya mantap, dagingnya empuk dan kuahnya hitam. Saya suka yang ada soun, tauge, dan kerupuknya. Bumbunya juga pas, tidak terlalu pedas atau hambar."),
    Review("Kue Cubit", R.drawable.cita2, 3, "Kue cubitnya lumayan, kuenya lembut dan toppingnya banyak. Saya suka yang coklat keju. Tapi, agak terlalu manis dan berminyak."),
    Review("Nasi Padang", R.drawable.cita2, 5, "Nasi padangnya juara, nasinya pulen dan lauknya banyak. Saya suka yang ada rendang, gulai, dan sambal hijaunya. Rasanya juga pas, tidak terlalu pedas atau hambar."),
    Review("Soto Ayam", R.drawable.cita2, 4, "Soto ayamnya enak, ayamnya empuk dan kuahnya kuning. Saya suka yang ada bihun, kentang, dan perkedelnya. Tapi, bawang gorengnya kurang banyak dan jeruk nipisnya kurang asam."),
    Review("Klepon", R.drawable.cita2, 4, "Kleponnya enak, kleponnya kenyal dan gula merahnya meleleh. Saya suka yang ada kelapa parutnya. Tapi, agak terlalu besar dan lengket.")
)

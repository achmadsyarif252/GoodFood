package com.example.goodfood.virtualforest

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.random.Random

@Composable
fun TreeCanvas() {
    Canvas(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {
        // Menghitung ukuran batang pohon dan daun
        val treeWidth = size.width / 10
        val treeHeight = size.height / 3
        val leafSize = size.width / 5

        // Posisi batang pohon
        val trunkLeft = (size.width - treeWidth) / 2
        val trunkRight = trunkLeft + treeWidth
        val trunkTop = size.height - treeHeight
        val trunkBottom = size.height

        // Gambar batang pohon
        drawRoundRect(
            color = Color(0xFF6B4226),
            topLeft = Offset(trunkLeft, trunkTop),
            size = Size(treeWidth, treeHeight),
            cornerRadius = CornerRadius(x = 20f, y = 20f)
        )

        // Gambar daun pohon
        val leafTop = trunkTop - leafSize / 2
        drawOval(
            color = Color(0xFF228B22),
            topLeft = Offset((size.width - leafSize) / 2, leafTop),
            size = Size(leafSize, leafSize)
        )
        drawOval(
            color = Color(0xFF228B22),
            topLeft = Offset((size.width - leafSize) / 2, leafTop - leafSize / 3),
            size = Size(leafSize, leafSize)
        )
        drawOval(
            color = Color(0xFF228B22),
            topLeft = Offset((size.width - leafSize) / 2, leafTop - 2 * leafSize / 3),
            size = Size(leafSize, leafSize)
        )
    }
}

@Preview
@Composable
fun PreviewTreeCanvas() {
    TreeCanvas()
}

@Composable
fun PineTreeCanvas() {
    Canvas(modifier = Modifier
        .fillMaxSize()) {
        val treeWidth = size.width / 10
        val treeHeight = size.height / 3
        val trunkLeft = (size.width - treeWidth) / 2
        val trunkRight = trunkLeft + treeWidth
        val trunkTop = size.height - treeHeight
        val trunkBottom = size.height

        // Gambar batang pohon
        drawRoundRect(
            color = Color(0xFF6B4226),
            topLeft = Offset(trunkLeft, trunkTop),
            size = Size(treeWidth, treeHeight),
            cornerRadius = CornerRadius(x = 20f, y = 20f)
        )

        // Gambar daun pohon cemara
        val leafWidth = size.width / 2
        val leafHeight = leafWidth / 2
        var leafTop = trunkTop

        // Menggunakan loop untuk menggambar segitiga yang mengecil ke atas
        for (i in 0 until 6) {  // Angka 6 dapat disesuaikan untuk lebih banyak layer
            val factor = (6 - i).toFloat() / 6  // Faktor pengecilan untuk setiap layer
            drawPath(
                path = Path().apply {
                    moveTo(size.width / 2f, leafTop)
                    lineTo(size.width / 2f - leafWidth * factor / 2, leafTop + leafHeight * factor)
                    lineTo(size.width / 2f + leafWidth * factor / 2, leafTop + leafHeight * factor)
                    close()
                },
                color = Color(0xFF228B22)
            )
            leafTop -= leafHeight * factor / 2  // Mengurangi tinggi untuk layer berikutnya
        }
        // Gambar tanah
        val groundHeight = size.height / 10  // Tinggi tanah
        drawRect(
            color = Color(0xFF964B00),
            topLeft = Offset(0f, size.height - groundHeight),
            size = Size(size.width, groundHeight)
        )
    }
}

@Preview
@Composable
fun PreviewPineTreeCanvas() {
    PineTreeCanvas()
}

@Composable
fun ForestCanvas() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        // Menentukan jumlah baris dan kolom
        val rows = 3
        val columns = 3
        val spacing = 50.dp.toPx()  // Jarak antar pohon

        // Menentukan ukuran pohon
        val treeWidth = size.width / 15
        val treeHeight = size.height / 6
        val leafWidth = size.width / 3
        val leafHeight = leafWidth / 2

        // Menggambar tanah
        val groundHeight = size.height / 8
        drawRect(
            color = Color(0xFF964B00),
            topLeft = Offset(0f, size.height - groundHeight),
            size = Size(size.width, groundHeight)
        )

        // Loop untuk menggambar pohon dalam baris dan kolom
        for (row in 0 until rows) {
            for (column in 0 until columns) {
                // Menghitung posisi x dan y untuk setiap pohon
                val x = spacing + (column * (size.width - 2 * spacing)) / (columns - 1)
                val y = spacing + (row * (size.height - groundHeight - 2 * spacing)) / (rows - 1)

                // Menggambar batang pohon
                drawRoundRect(
                    color = Color(0xFF6B4226),
                    topLeft = Offset(x - treeWidth / 2, y),
                    size = Size(treeWidth, treeHeight),
                    cornerRadius = CornerRadius(x = 20f, y = 20f)
                )

                // Menggambar daun pohon cemara
                var leafTop = y - leafHeight / 2

                // Menggunakan loop untuk menggambar segitiga yang mengecil ke atas
                for (i in 0 until 6) {  // Angka 6 dapat disesuaikan untuk lebih banyak layer
                    val factor = (6 - i).toFloat() / 6  // Faktor pengecilan untuk setiap layer
                    drawPath(
                        path = Path().apply {
                            moveTo(x, leafTop)
                            lineTo(x - leafWidth * factor / 2, leafTop + leafHeight * factor)
                            lineTo(x + leafWidth * factor / 2, leafTop + leafHeight * factor)
                            close()
                        },
                        color = Color(0xFF228B22)
                    )
                    leafTop -= leafHeight * factor / 2  // Mengurangi tinggi untuk layer berikutnya
                }
            }
        }
    }
}

@Composable
fun ForestCanvas2() {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val treeCount = 9  // Total jumlah pohon
        val treeWidth = size.width / 15
        val treeHeight = size.height / 6
        val leafWidth = size.width / 3
        val leafHeight = leafWidth / 2

        // Menggambar tanah
        val groundHeight = size.height / 8
        drawRect(
            color = Color(0xFF964B00),
            topLeft = Offset(0f, size.height - groundHeight),
            size = Size(size.width, groundHeight)
        )

        // Loop untuk menggambar jumlah pohon yang ditentukan dengan posisi acak
        repeat(treeCount) {
            // Menghitung posisi x dan y acak untuk setiap pohon
            val x = Random.nextFloat() * (size.width - treeWidth)
            val y = Random.nextFloat() * (size.height - groundHeight - treeHeight) + groundHeight

            // Menggambar batang pohon
            drawRoundRect(
                color = Color(0xFF6B4226),
                topLeft = Offset(x - treeWidth / 2, y - treeHeight),
                size = Size(treeWidth, treeHeight),
                cornerRadius = CornerRadius(x = 20f, y = 20f)
            )

            // Menggambar daun pohon cemara
            var leafTop = y - treeHeight - leafHeight / 2

            // Menggunakan loop untuk menggambar segitiga yang mengecil ke atas
            for (i in 0 until 6) {
                val factor = (6 - i).toFloat() / 6  // Faktor pengecilan untuk setiap layer
                drawPath(
                    path = Path().apply {
                        moveTo(x, leafTop)
                        lineTo(x - leafWidth * factor / 2, leafTop + leafHeight * factor)
                        lineTo(x + leafWidth * factor / 2, leafTop + leafHeight * factor)
                        close()
                    },
                    color = Color(0xFF228B22)
                )
                leafTop -= leafHeight * factor / 2  // Mengurangi tinggi untuk layer berikutnya
            }
        }
    }
}

@Preview
@Composable
fun PreviewForestCanvas() {
    ForestCanvas2()
}

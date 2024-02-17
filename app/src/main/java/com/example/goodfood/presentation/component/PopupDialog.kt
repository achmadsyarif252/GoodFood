package com.example.goodfood.presentation.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun RatingDialog(
    onDismiss: () -> Unit, // fungsi yang dipanggil ketika dialog ditutup
    onSubmit: (Float) -> Unit, // fungsi yang dipanggil ketika rating dikirim
    foodName: String, // nama makanan yang akan direview
    foodImage: Int, // gambar makanan yang akan direview
    dialogOpen: Boolean, // state yang menyimpan apakah dialog terbuka atau tidak
) {
    val ctx = LocalContext.current
    // state untuk menyimpan nilai rating
    var rating by rememberSaveable { mutableStateOf(0f) }
    var isError by rememberSaveable { mutableStateOf(false) }


    // state untuk menyimpan textfield
    var textFieldState by remember {
        mutableStateOf("")
    }
    // jika dialog terbuka, tampilkan AlertDialog
    if (dialogOpen) {
        AlertDialog(
            containerColor = Color.White,
            onDismissRequest = {
                // tutup dialog ketika pengguna menekan tombol kembali atau klik di luar dialog
                onDismiss()
            },
            title = {
                // tampilkan judul dialog dengan nama makanan
                Text(text = "Berikan rating untuk $foodName")
            },
            text = {
                // tampilkan konten dialog dengan gambar makanan dan bintang rating
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // tampilkan gambar makanan dengan ukuran 100 x 100 dp
                    Image(
                        painter = painterResource(id = foodImage),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        isError = isError,
                        value = textFieldState, onValueChange = {
                            textFieldState = it
                            if (it.isNotEmpty()) isError = false

                        },
                        placeholder = {
                            Text(text = if (!isError) "Tulis Review Anda" else "Tidak Boleh Kosong")
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = if (isError) Color.Red else Color.Blue, // Change border color if error
                            unfocusedBorderColor = if (isError) Color.Red else Color.Gray,
                        ),
                        trailingIcon = {
                            if (isError) {
                                Icon(Icons.Filled.Clear, "Error", tint = Color.Red)
                            }
                        })


                    Spacer(modifier = Modifier.height(8.dp))

                    // tampilkan bintang rating dengan ukuran 36 dp
                    RatingBar(
                        rating = rating,
                        onRatingChanged = { rating = it },
                        size = 36.dp,
                        padding = 4.dp
                    )
                }
            },
            confirmButton = {
                // tampilkan tombol kirim dengan teks "Kirim"
                TextButton(onClick = {
                    // kirim rating ke fungsi onSubmit dan tutup dialog
                    if (textFieldState.isEmpty()) {
                        isError = true
                    } else {
                        isError = false
                        onSubmit(rating)
                    }
                }) {
                    Text(text = "Kirim")
                }
            },
            dismissButton = {
                // tampilkan tombol batal dengan teks "Batal"
                TextButton(onClick = {
                    // tutup dialog tanpa mengirim rating
                    onDismiss()
                }) {
                    Text(text = "Batal")
                }
            }
        )
    }
}

// komponen untuk menampilkan bintang rating
@Composable
fun RatingBar(
    rating: Float, // nilai rating antara 0 sampai 5
    onRatingChanged: (Float) -> Unit, // fungsi yang dipanggil ketika rating berubah
    size: Dp, // ukuran bintang
    padding: Dp, // jarak antara bintang
    activeColor: Color = Color.Yellow, // warna bintang aktif
    inactiveColor: Color = Color.Gray // warna bintang tidak aktif
) {
    // buat sebuah row untuk menampung bintang
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .height(size)
            .wrapContentWidth()
    ) {
        // buat lima bintang dengan index dari 1 sampai 5
        for (i in 1..5) {
            // tentukan warna bintang berdasarkan rating
            val color = if (rating >= i) {
                // jika rating lebih besar atau sama dengan index, gunakan warna aktif
                activeColor
            } else if (rating > i - 1 && rating < i) {
                // jika rating berada di antara index dan index - 1, gunakan warna campuran
                lerp(activeColor, inactiveColor, i - rating)
            } else {
                // jika rating lebih kecil dari index - 1, gunakan warna tidak aktif
                inactiveColor
            }
            // tampilkan bintang dengan warna yang ditentukan
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                tint = color,
                modifier = Modifier
                    .size(size)
                    .padding(end = padding)
                    .clickable {
                        // ubah rating ketika bintang diklik
                        onRatingChanged(i.toFloat())
                    }
            )
        }
    }
}
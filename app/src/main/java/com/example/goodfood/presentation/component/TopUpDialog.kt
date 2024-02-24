package com.example.goodfood.presentation.component

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.goodfood.R
import com.example.goodfood.ReviewViewModel
import com.example.goodfood.WalletViewModel
import com.example.goodfood.domain.model.Food
import com.example.goodfood.domain.model.MyWallet
import com.example.goodfood.domain.model.Review
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TopUpDialog(
    onDismiss: () -> Unit, // fungsi yang dipanggil ketika dialog ditutup
    onSubmit: (Float) -> Unit, // fungsi yang dipanggil ketika rating dikirim
    wallet: MyWallet,
    dialogOpen: Boolean, // state yang menyimpan apakah dialog terbuka atau tidak
    walletViewModel: WalletViewModel = viewModel()
) {
    val ctx = LocalContext.current
    // state untuk menyimpan nilai rating
    var rating by rememberSaveable { mutableStateOf(0f) }
    var isError by rememberSaveable { mutableStateOf(false) }


    // state untuk menyimpan textfield
    var textFieldState by remember {
        mutableStateOf("")
    }

    // Function to format input to Rupiah
    fun formatToRupiah(input: String): String {
        return try {
            val number = input.filter { it.isDigit() }.toDouble()
            val localeID = Locale("in", "ID")
            val numberFormat = NumberFormat.getNumberInstance(localeID)
            numberFormat.maximumFractionDigits = 0 // Assuming no decimal places
            numberFormat.format(number)
        } catch (e: NumberFormatException) {
            "" // Return empty string if conversion fails
        }
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
                Text(text = "TopUp This Card ${wallet.wallet.name}")
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
                        painter = painterResource(id = wallet.wallet.image),
                        contentDescription = null,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = isError,
                        value = textFieldState, onValueChange = {
                            val numericOnly =
                                it.filter { char -> char.isDigit() || char == ',' || char == '.' }
                            textFieldState = formatToRupiah(numericOnly)
                            if (it.isNotEmpty()) isError = false

                        },
                        placeholder = {
                            Text(text = if (!isError) "Masukkan Jumlah" else "Tidak Boleh Kosong")
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

                    OutlinedTextField(
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = isError,
                        value = textFieldState, onValueChange = {
                            textFieldState = it
                            if (it.isNotEmpty()) isError = false

                        },
                        placeholder = {
                            Text(text = if (!isError) "Masukkan PIN" else "Tidak Boleh Kosong")
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
                }
            },
            confirmButton = {
                // tampilkan tombol kirim dengan teks "Kirim"
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue,
                        contentColor = Color.White
                    ),
                    onClick = {

                    }) {
                    Text(text = "Top Up")
                }

            },
            dismissButton = {
                // tampilkan tombol batal dengan teks "Batal"
                OutlinedButton(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
                    onClick = {

                    }) {
                    Text(text = "Batal")
                }
            }
        )
    }
}

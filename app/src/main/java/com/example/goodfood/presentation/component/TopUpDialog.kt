package com.example.goodfood.presentation.component

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.example.goodfood.core.domain.model.MyWallet
import java.text.NumberFormat
import java.util.Locale

@Composable
fun TopUpDialog(
    onDismiss: () -> Unit, // fungsi yang dipanggil ketika dialog ditutup
    onSubmit: (Double, String, wallet: MyWallet) -> Unit, // fungsi yang dipanggil ketika rating dikirim
    wallet: MyWallet,
    dialogOpen: Boolean, // state yang menyimpan apakah dialog terbuka atau tidak
) {
    // state untuk menyimpan nilai rating
    var isError by rememberSaveable { mutableStateOf(false) }

    // state untuk menyimpan textfield
    var textFieldStateAmount by remember {
        mutableStateOf("")
    }

    var textFieldStatePIN by remember {
        mutableStateOf("")
    }

    val ctx = LocalContext.current
    fun formatToRupiah(input: String): String {
        return try {
            val number = input.filter { it.isDigit() }.toDouble()
            val localeID = Locale("in", "ID")
            val numberFormat = NumberFormat.getNumberInstance(localeID)
            numberFormat.maximumFractionDigits = 0 // Assuming no decimal places
            numberFormat.format(number)

        } catch (e: NumberFormatException) {
            "Rp" // Return empty string if conversion fails
        }
    }

    fun rupiahStringToDouble(rupiahString: String): Double? {
        // Remove the currency symbol and any other non-numeric characters except the decimal separator
        val numericString = rupiahString.replace("[^\\d.]".toRegex(), "")

        return try {
            // Attempt to parse the cleaned string as a Double
            numericString.toDouble()
        } catch (e: NumberFormatException) {
            // Return null or handle the exception if the string cannot be parsed
            null
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
                        value = textFieldStateAmount, onValueChange = {
                            textFieldStateAmount = it
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
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                        isError = isError,
                        value = textFieldStatePIN, onValueChange = {
                            if (it.length <= 6) {
                                textFieldStatePIN = it
                                if (it.isNotEmpty()) isError = false
                            } else {
                                // If the input exceeds 6 characters, keep the previous state (effectively ignoring the new input)
                                // Or you could truncate it to 6 characters, but that might not be necessary for a PIN input scenario
                                textFieldStatePIN =
                                    it.take(6) // Optional: Uncomment this if you want to automatically truncate to 6 characters
                            }

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
                        if (textFieldStatePIN == "020505") Toast.makeText(
                            ctx,
                            "PIN Salah",
                            Toast.LENGTH_SHORT
                        ).show()
                        else {
                            val convertedValue = rupiahStringToDouble(textFieldStateAmount)
                            if (convertedValue != null) {
                                onSubmit(convertedValue.toDouble(), textFieldStatePIN, wallet)
                            }
                            textFieldStatePIN = ""
                            textFieldStateAmount = ""
                        }


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
                        textFieldStatePIN = ""
                        textFieldStateAmount = ""
                        onDismiss()
                    }) {
                    Text(text = "Batal")
                }
            }
        )
    }
}

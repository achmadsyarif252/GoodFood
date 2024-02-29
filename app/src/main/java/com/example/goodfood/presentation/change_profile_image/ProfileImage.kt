package com.example.goodfood.presentation.change_profile_image

import android.net.Uri
import android.os.Environment
import android.util.Log
import android.widget.Space
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.FileProvider
import androidx.core.net.toUri
import com.example.goodfood.R
import java.io.File

@Composable
fun ProfileImage(modifier: Modifier = Modifier) {

    val context = LocalContext.current

    val photoFile = File(
        context.getExternalFilesDir(Environment.DIRECTORY_PICTURES),
        "photo_${System.currentTimeMillis()}.jpg"
    )
    val photoUri = FileProvider.getUriForFile(context, "com.example.goodfood.provider", photoFile)


    val takePictureLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.TakePicture()) { success: Boolean ->
            if (success) {
                Log.d("Malam Jumat", "$photoUri" + " KAMERA")
            } else {
                Log.d("Malam Jumat", "$photoUri" + " KAMERA FAILED")

            }
        }
    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            // Konversi URI ke path yang sesuai untuk disimpan di database
            Log.d("Malam Jumat", it.path.toString() + " OKOK")
//            val path = convertUriToPath(uri)
            // Simpan path ini dalam state atau variable sementara
            // untuk disimpan ke database ketika pengguna menekan tombol save
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
            painter = painterResource(id = R.drawable.cita2), contentDescription = null
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 32.dp)
                .align(Alignment.BottomCenter)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedButton(
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue
                ),
                modifier = Modifier.weight(1f), // This will make the button take up the remaining space
                onClick = {
                    pickImageLauncher.launch("image/*")
                }) {
                Text(text = "Change From Gallery")
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.aperture),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        takePictureLauncher.launch(photoUri)
                    },

                )
        }
    }
}
package com.example.goodfood.presentation.change_profile_image

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.goodfood.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ProfileImage(
    imageUri: Uri?,
    onPickImage: () -> Unit,
    onTakePicture: () -> Unit,
    launchCameraPermission: () -> Unit
) {
    val context = LocalContext.current



    Box(modifier = Modifier.fillMaxSize()) {
        if (imageUri == null)
            Image(
                painter = painterResource(id = R.drawable.cita2),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )
        else
            imageUri.let { uri ->
                val bitmap = loadPicture(uri = uri).value
                bitmap?.let {
                    Image(
                        bitmap = it,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Fit
                    )
                }

            }
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
                    onPickImage()
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
                        when (PackageManager.PERMISSION_GRANTED) {
                            ContextCompat.checkSelfPermission(
                                context,
                                Manifest.permission.CAMERA
                            ) -> {
                                onTakePicture()
                            }

                            else -> {
                                // Minta izin kamera
                                launchCameraPermission()

                            }
                        }
//                        onTakePicture()
                    },

                )
        }
    }
}

@Composable
fun loadPicture(uri: Uri): State<ImageBitmap?> {
    val imageBitmapState = remember { mutableStateOf<ImageBitmap?>(null) }
    val context = LocalContext.current

    CoilImageLoader(context, uri) { imageBitmap ->
        imageBitmapState.value = imageBitmap
    }

    return imageBitmapState
}

fun CoilImageLoader(context: Context, uri: Uri, onImageLoaded: (ImageBitmap?) -> Unit) {
    val imageLoader = ImageLoader(context)
    val request = ImageRequest.Builder(context)
        .data(uri)
        .allowHardware(false) // Important for compatibility with Compose ImageBitmap
        .target { drawable ->
            val bitmap = (drawable as? android.graphics.drawable.BitmapDrawable)?.bitmap
            onImageLoaded(bitmap?.asImageBitmap())
        }
        .build()

    CoroutineScope(Dispatchers.IO).launch {
        val result = imageLoader.execute(request)
        if (result is SuccessResult) {
            onImageLoaded(result.drawable.toBitmap().asImageBitmap())
        }
    }
}
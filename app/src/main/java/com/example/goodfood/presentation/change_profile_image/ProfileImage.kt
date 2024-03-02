package com.example.goodfood.presentation.change_profile_image

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
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
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.toBitmap
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.example.goodfood.LoginViewModel
import com.example.goodfood.R
import com.example.goodfood.RegisterViewModel
import com.example.goodfood.data.LoginInfo
import com.example.goodfood.data.UserViewModelFactory
import com.example.goodfood.ui.theme.Gold
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

@Composable
fun ProfileImage(
    imageUri: Uri?,
    onPickImage: () -> Unit,
    onTakePicture: () -> Unit,
    userViewModel: RegisterViewModel = viewModel(),
    reqPermission: () -> Unit
) {
    var isUserPhotoEmpty by remember {
        mutableStateOf(imageUri == null)
    }
    val context = LocalContext.current
    val viewModel: LoginViewModel = viewModel(
        factory = UserViewModelFactory(context)
    )
    val loginInfo by viewModel.loginInfo.observeAsState(LoginInfo(false, ""))

    val accountInfo by userViewModel.isAlreadyExist(
        email = loginInfo.username,
    ).observeAsState()

    LaunchedEffect(key1 = 1) {
        reqPermission()
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (imageUri == null) {
            if (accountInfo?.imagePath != null) {
                val bitmap = loadBitmapFromStorage(accountInfo!!.imagePath!!)?.asImageBitmap()
                bitmap?.let {
                    Image(bitmap = it, contentDescription = null)
                }
            } else {
                Image(
                    painter = painterResource(R.drawable.cita2),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        } else
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
            Column(modifier = Modifier.weight(1f)) {
                if (!isUserPhotoEmpty) OutlinedButton(
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Gold
                    ),
                    modifier = Modifier.fillMaxWidth(1f), // This will make the button take up the remaining space
                    onClick = {
                        imageUri?.let {
                            accountInfo?.copy(imagePath = it.path.toString())
                                ?.let { it1 -> userViewModel.update(it1) }
                        }
                    }) {
                    Text(text = "Save")
                }
                OutlinedButton(
                    shape = RoundedCornerShape(8.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Blue
                    ),
                    modifier = Modifier.fillMaxWidth(1f), // This will make the button take up the remaining space
                    onClick = {
                        onPickImage()
                    }) {
                    Text(text = "Change From Gallery")
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Image(
                painter = painterResource(id = R.drawable.aperture),
                contentDescription = "",
                modifier = Modifier
                    .size(24.dp)
                    .clickable {
                        onTakePicture()
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

suspend fun convertUriToPath(context: Context, contentUri: Uri): Uri {
    val inputStream = context.contentResolver.openInputStream(contentUri)
    val fileName = "note_image_${System.currentTimeMillis()}.jpg"
    val newFile = File(context.filesDir, fileName)
    val outputStream = withContext(Dispatchers.IO) {
        FileOutputStream(newFile)
    }

    inputStream?.use { input ->
        outputStream.use { output ->
            input.copyTo(output)
        }
    }

    return Uri.fromFile(newFile)
}

fun loadBitmapFromStorage(path: String): Bitmap? {
    return BitmapFactory.decodeFile(path)
}

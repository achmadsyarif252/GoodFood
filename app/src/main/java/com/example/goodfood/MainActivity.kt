package com.example.goodfood

import android.Manifest
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.FileProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goodfood.data.LoginInfo
import com.example.goodfood.data.UserViewModelFactory
import com.example.goodfood.presentation.cart.CartScreen
import com.example.goodfood.presentation.change_profile_image.ProfileImage
import com.example.goodfood.presentation.detail.DetailScreen
import com.example.goodfood.presentation.favorite.FavoriteScreen
import com.example.goodfood.presentation.home.HomeScreen
import com.example.goodfood.presentation.login.LoginScreen
import com.example.goodfood.presentation.nearby_restaurant.DetailResto
import com.example.goodfood.presentation.nearby_restaurant.NearbyRestaurant
import com.example.goodfood.presentation.payment.PaymentScreen
import com.example.goodfood.presentation.profile.ProfileScreen
import com.example.goodfood.presentation.register.SignUpScreen
import com.example.goodfood.presentation.review.ReviewScreen
import com.example.goodfood.presentation.topupscreen.SavingAccountScreen
import com.example.goodfood.ui.theme.FoodAppsTheme
import java.io.File
import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Context
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.runtime.LaunchedEffect
import androidx.core.content.ContextCompat

class MainActivity : ComponentActivity() {
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    // Izin diberikan, handle sesuai kebutuhan
                } else {
                    // Izin ditolak, tampilkan pesan atau handle penolakan
                }
            }

        enableEdgeToEdge()
        setContent {
            FoodAppsTheme {
                MyApp(requestPermission = { requestPermissionLauncher.launch(READ_EXTERNAL_STORAGE) })
            }
        }
    }
}

// Membuat sebuah CompositionLocalOf<NavController>
val LocalNavController = compositionLocalOf<NavController> { error("No NavController found") }

@Composable
fun MyApp(
    modifier: Modifier = Modifier, requestPermission: () -> Unit,
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = 1) {
        if (!hasStoragePermission(context)) {
            Log.d("MALAM MINGGU CUY", "OKOKOK")
            requestPermission()
        } else {
            Log.d("MALAM MINGGU CUY", "SUDAH ACC")

        }
    }
    val viewModel: LoginViewModel = viewModel(
        factory = UserViewModelFactory(context)
    )
    val loginInfo by viewModel.loginInfo.observeAsState(LoginInfo(false, ""))
    // Membuat sebuah NavController
    val navController = rememberNavController()
    // Membuat sebuah NavHost dengan NavController dan startDestination

    //property for profile image
    var imageUri by remember {
        mutableStateOf<Uri?>(null)
    }

    val externalCacheDir = context.externalCacheDir?.absolutePath

    val pickImageLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? -> imageUri = uri })

    val takePictureLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicture(),
        onResult = { success: Boolean ->
            if (success) {
                imageUri = FileProvider.getUriForFile(
                    context,
                    "com.example.goodfood.provider",
                    File(externalCacheDir, "new-photo.jpg")
                )
            }
        }
    )


    val startDestination =
        if (loginInfo.isLoggedIn) "home" else "login"
    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = startDestination) {
            // Menambahkan rute dan layar yang bisa dinavigasi ke dalam NavHost
            // Menambahkan rute dan layar home
            composable("login") {
                LoginScreen()
            }
            composable("home") {
                HomeScreen()
            }
            // Menambahkan rute dan layar detail
            composable("detail/{index}") {
                it.arguments?.getString("index")?.let { index ->
                    DetailScreen(navController, foodIndex = index)
                }
            }

            composable("detailresto/{index}") {
                it.arguments?.getString("index")?.let { index ->
                    DetailResto(restaurantIndex = index)
                }
            }
            composable("cart") {
                CartScreen()
            }
            composable("favorite") {
                FavoriteScreen()
            }
            composable("payment") {
                PaymentScreen()
            }
            composable("review") {
                ReviewScreen()
            }
            composable("nearbyResto") {
                NearbyRestaurant()
            }
            composable("profile") {
                ProfileScreen()
            }
            composable("topup") {
                SavingAccountScreen()
            }
            composable("login") {
                LoginScreen()
            }
            composable("register") {
                SignUpScreen()
            }
            composable("change_profile_pic") {
                ProfileImage(
                    imageUri = imageUri,
                    onPickImage = { pickImageLauncher.launch("image/*") },
                    onTakePicture = {
                        val photoFile = File(context.externalCacheDir, "new-photo.jpg")
                        val photoUri = FileProvider.getUriForFile(
                            context,
                            "${context.applicationContext.packageName}.provider",
                            photoFile
                        )
                        takePictureLauncher.launch(photoUri)
                    },
                    reqPermission = {

                    }
                )
            }
        }
    }
}


fun hasStoragePermission(context: Context): Boolean {
    return ContextCompat.checkSelfPermission(
        context,
        READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}



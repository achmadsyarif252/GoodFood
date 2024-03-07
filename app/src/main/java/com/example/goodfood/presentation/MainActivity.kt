package com.example.goodfood.presentation

import android.Manifest
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import com.example.goodfood.core.data.LoginInfo
import com.example.goodfood.presentation.cart.CartScreen
import com.example.goodfood.presentation.change_profile_image.ProfileImage
import com.example.goodfood.presentation.detail.DetailScreen
import com.example.goodfood.presentation.favorite.FavoriteScreen
import com.example.goodfood.presentation.home.HomeScreen
import com.example.goodfood.presentation.login.LoginScreen
import com.example.goodfood.presentation.login.LoginViewModel
import com.example.goodfood.presentation.nearby_restaurant.DetailResto
import com.example.goodfood.presentation.nearby_restaurant.NearbyRestaurant
import com.example.goodfood.presentation.payment.PaymentScreen
import com.example.goodfood.presentation.profile.ProfileScreen
import com.example.goodfood.presentation.register.SignUpScreen
import com.example.goodfood.presentation.review.ReviewScreen
import com.example.goodfood.presentation.review.ReviewViewModel
import com.example.goodfood.presentation.topupscreen.SavingAccountScreen
import com.example.goodfood.ui.theme.FoodAppsTheme
import java.io.File


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FoodAppsTheme {
                MyApp()
            }
        }
    }
}

// Membuat sebuah CompositionLocalOf<NavController>
val LocalNavController = compositionLocalOf<NavController> { error("No NavController found") }

@Composable
fun MyApp(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val factory = FoodViewModelFactory.getInstance()
    val reviewViewModel: ReviewViewModel = viewModel(factory = factory)

    val viewModel: LoginViewModel = viewModel(
        factory = UserViewModelFactory(context)
    )
    var username by rememberSaveable { mutableStateOf("") }
    val loginInfo by viewModel.loginInfo.observeAsState(LoginInfo(false, ""))
    val allReviews by reviewViewModel.allReviewEntity.observeAsState(initial = emptyList())

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

    val cameraPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission(),
        onResult = { isGranted: Boolean ->
            if (isGranted) {
                // Izin diberikan, lanjutkan untuk membuka kamera
                val photoFile = File(context.externalCacheDir, "new-photo.jpg")
                imageUri = FileProvider.getUriForFile(
                    context,
                    "${context.packageName}.provider",
                    photoFile
                )
                takePictureLauncher.launch(imageUri)
            } else {
                Toast.makeText(
                    context,
                    "Camera permission is required to use the camera",
                    Toast.LENGTH_SHORT
                ).show()
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
                    }, launchCameraPermission = {
                        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)
                    })
            }
        }
    }
}


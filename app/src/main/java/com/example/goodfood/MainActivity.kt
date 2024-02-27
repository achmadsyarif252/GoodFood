package com.example.goodfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goodfood.presentation.cart.CartScreen
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
fun MyApp(modifier: Modifier = Modifier, reviewViewModel: ReviewViewModel = viewModel()) {

    val allReviews by reviewViewModel.allReview.observeAsState(initial = emptyList())

    // Membuat sebuah NavController
    val navController = rememberNavController()
    // Membuat sebuah NavHost dengan NavController dan startDestination

    CompositionLocalProvider(LocalNavController provides navController) {
        NavHost(navController = navController, startDestination = "home") {
            // Menambahkan rute dan layar yang bisa dinavigasi ke dalam NavHost
            // Menambahkan rute dan layar home
            composable("home") {
                LoginScreen()
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

        }
    }
}



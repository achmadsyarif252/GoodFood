package com.example.goodfood

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.goodfood.domain.model.listFood
import com.example.goodfood.presentation.detail.DetailScreen
import com.example.goodfood.presentation.home.HomeScreen
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

@Composable
fun MyApp(modifier: Modifier = Modifier) {
    // Membuat sebuah NavController
    val navController = rememberNavController()
    // Membuat sebuah NavHost dengan NavController dan startDestination
    NavHost(navController = navController, startDestination = "home") {
        // Menambahkan rute dan layar yang bisa dinavigasi ke dalam NavHost

        // Menambahkan rute dan layar home
        composable("home") {
            HomeScreen(navController)
        }
        // Menambahkan rute dan layar detail
        composable("detail") {
            DetailScreen(navController, listFood[0])
        }
    }
}



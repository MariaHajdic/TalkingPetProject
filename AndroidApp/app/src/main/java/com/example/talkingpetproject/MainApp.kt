package com.example.talkingpetproject

import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

@Composable
fun MainApp() {
    val navController = rememberNavController()
    Navigation(navController)
}

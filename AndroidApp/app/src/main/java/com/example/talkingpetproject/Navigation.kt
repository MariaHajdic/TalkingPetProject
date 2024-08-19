package com.example.talkingpetproject

import LoginScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screen.LoginScreen.route) {
        composable(route = Screen.LoginScreen.route) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.ButtonScreen.route) { backStackEntry ->
            val buttonId = backStackEntry.arguments?.getString("buttonId")?.toInt() ?: 0
            ButtonScreen(navController = navController, buttonId = buttonId)
        }
        composable(route = Screen.AllEventsScreen.route) {
            AllEventsScreen(navController = navController)
        }
    }
}

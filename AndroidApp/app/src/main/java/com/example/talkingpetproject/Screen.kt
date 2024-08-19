package com.example.talkingpetproject

sealed class Screen(val route: String) {
    object LoginScreen : Screen("login_screen")
    object MainScreen : Screen("main_screen")
    object ButtonScreen : Screen("button_screen/{buttonId}") {
        fun createRoute(buttonId: Int) = "button_screen/$buttonId"
    }
    object AllEventsScreen : Screen("all_events_screen")
}
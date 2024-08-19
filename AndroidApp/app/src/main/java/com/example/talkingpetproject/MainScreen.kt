package com.example.talkingpetproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navController: NavHostController) {
    val events = remember { generateSampleEvents().reversed() }
    val bottomNavItems = listOf("Button1", "Button2", "Button3")
    val icons = listOf(Icons.Default.Home, Icons.Default.Favorite, Icons.Default.Person)
    var selectedItemIndex = rememberSaveable { 0 }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            BottomNavigationBar(
                items = bottomNavItems,
                icons = icons,
                selectedIndex = selectedItemIndex,
                onItemSelected = { index ->
                    selectedItemIndex = index
                    navController.navigate(Screen.ButtonScreen.createRoute(index))
                }
            )
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            EventList(events.take(10))
            ShowMoreButton {
                navController.navigate(Screen.AllEventsScreen.route)
            }
        }
    }
}

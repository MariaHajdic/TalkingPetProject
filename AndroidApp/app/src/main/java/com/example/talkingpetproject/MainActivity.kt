package com.example.talkingpetproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.talkingpetproject.ui.theme.TalkingPetProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TalkingPetProjectTheme {
                MainApp()
            }
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
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

@Composable
fun LoginScreen(navController: NavHostController) {
    Button(onClick = { navController.navigate(Screen.MainScreen.route) }) {
        Text("Login")
    }
}

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

@Composable
fun BottomNavigationBar(
    items: List<String>,
    icons: List<ImageVector>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(icons[index], contentDescription = item) },
                label = { Text(item) },
                selected = selectedIndex == index,
                onClick = { onItemSelected(index) }
            )
        }
    }
}

@Composable
fun EventList(events: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(events) { event ->
            Text(text = event, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.padding(8.dp))
        }
    }
}

@Composable
fun ShowMoreButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text("Show More")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AllEventsScreen(navController: NavHostController) {
    val events = remember { generateSampleEvents().reversed() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("All Events") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { innerPadding ->
        EventList(events, Modifier.padding(innerPadding))
    }
}

@Composable
fun ButtonScreen(navController: NavHostController, buttonId: Int) {
    Text(text = "Button $buttonId clicked")
}

fun generateSampleEvents(): List<String> {
    return List(20) { index -> "Event ${index + 1}" }
}

@Preview(showBackground = true)
@Composable
fun MainAppPreview() {
    TalkingPetProjectTheme {
        MainApp()
    }
}

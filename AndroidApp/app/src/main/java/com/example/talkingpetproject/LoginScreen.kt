import MainScreen
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.navigation.NavHostController

@Composable
fun LoginScreen(navController: NavHostController) {
    Button(onClick = { navController.navigate(Screen.MainScreen.route) }) {
        Text("Login")
    }
}
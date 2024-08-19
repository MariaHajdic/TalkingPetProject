package com.example.talkingpetproject

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material3.*

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

package com.example.talkingpetproject

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.ui.unit.dp

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

package org.example.personalfinancetracker.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun MainScreen(
    modifier: Modifier = Modifier
) {
    Box(modifier) {
        Text(text = "MainScreen")
    }
}

@Preview(name = "MainScreen")
@Composable
private fun PreviewMainScreen() {
    MainScreen()
}
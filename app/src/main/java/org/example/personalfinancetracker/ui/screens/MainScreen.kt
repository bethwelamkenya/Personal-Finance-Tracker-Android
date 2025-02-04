package org.example.personalfinancetracker.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun MainScreen(
    navController: NavHostController
) {
    Box {
        Text(text = "MainScreen")
    }
}

@Preview(name = "MainScreen")
@Composable
private fun PreviewMainScreen() {
    val navController = rememberNavController()
    MainScreen(navController)
}
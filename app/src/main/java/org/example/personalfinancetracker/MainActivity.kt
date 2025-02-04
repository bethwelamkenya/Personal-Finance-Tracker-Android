package org.example.personalfinancetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHost
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import org.example.personalfinancetracker.ui.screens.LoginScreen
import org.example.personalfinancetracker.ui.screens.MainScreen
import org.example.personalfinancetracker.ui.theme.PersonalFinanceTrackerTheme

@HiltAndroidApp
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersonalFinanceTrackerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // Initialize dependencies
                    val dependencies = AppDependencies().apply {
                        key =
                            encryptionHelper.getKeyFromString(resources.getString(R.string.app_key))
                    }
                    Greeting(
                        modifier = Modifier.padding(innerPadding), dependencies = dependencies
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier, dependencies: AppDependencies) {
    val navController = rememberNavController()
    val isLoggedIn = false // Replace with actual auth state check

    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "main" else "login"
    ) {
        composable("login") { LoginScreen(navController) }
        composable("main") { MainScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PersonalFinanceTrackerTheme {
        val dependencies = AppDependencies()
        Greeting(Modifier, dependencies = dependencies)
    }
}
package org.example.personalfinancetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.example.personalfinancetracker.ui.screens.LoginScreen
import org.example.personalfinancetracker.ui.screens.MainScreen
import org.example.personalfinancetracker.ui.theme.PersonalFinanceTrackerTheme
import org.example.personalfinancetracker.viewModels.HomeViewModel
import org.example.personalfinancetracker.viewModels.LoginViewModel

//@HiltAndroidApp
//@AndroidEntryPoint
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
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val authRepository = FirebaseAuthRepository(auth, firestore, dependencies)
    val logInViewModel = LoginViewModel(authRepository)
    val homeViewModel = HomeViewModel(authRepository)
    NavHost(
        navController = navController,
        startDestination = if (isLoggedIn) "main" else "login",
        modifier = modifier
    ) {
        composable("login") { LoginScreen(navController, logInViewModel) }
        composable("main") { MainScreen(navController, homeViewModel) }
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
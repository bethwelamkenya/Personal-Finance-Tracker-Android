package org.example.personalfinancetracker.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import org.example.personalfinancetracker.AppDependencies
import org.example.personalfinancetracker.FirebaseAuthRepository
import org.example.personalfinancetracker.viewModels.HomeViewModel

@Composable
fun MainScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel
) {
    Box {
        Text(text = "MainScreen")
    }
}

@Preview(name = "MainScreen")
@Composable
private fun PreviewMainScreen() {
    val navController = rememberNavController()
    val auth = FirebaseAuth.getInstance()
    val firestore = FirebaseFirestore.getInstance()
    val authRepository = FirebaseAuthRepository(auth, firestore, AppDependencies())
    val homeViewModel = HomeViewModel(authRepository)
    MainScreen(navController, homeViewModel)
}
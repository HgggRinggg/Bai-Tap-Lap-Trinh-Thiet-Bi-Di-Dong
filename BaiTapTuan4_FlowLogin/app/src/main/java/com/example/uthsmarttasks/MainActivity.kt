package com.example.uthsmarttasks

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.uthsmarttasks.ui.screens.ProfileScreen
import com.example.uthsmarttasks.ui.screens.SignInWithGoogle
import com.example.uthsmarttasks.ui.screens.SignInWithGoogleUI
import com.example.uthsmarttasks.ui.theme.UTHSmartTasksTheme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        FirebaseApp.initializeApp(this)
        setContent {
            val navController = rememberNavController()
            UTHSmartTasksTheme {
                AppNavHost(navController)
            }
        }
    }
}

@Composable
fun AppNavHost(navController: NavHostController)
{
    NavHost(navController, startDestination = "login")
    {
        composable("login") { SignInWithGoogle(navController) }
        composable("profile") { ProfileScreen(navController) }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    UTHSmartTasksTheme {
        SignInWithGoogleUI(onSignInClick = {})
    }
}
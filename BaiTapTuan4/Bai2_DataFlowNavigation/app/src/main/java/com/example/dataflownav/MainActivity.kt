package com.example.dataflownav

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dataflownav.screens.ConfirmScreen
import com.example.dataflownav.screens.CreateNewPassScreen
import com.example.dataflownav.screens.ForgetPassScreen
import com.example.dataflownav.screens.VerifyCodeScreen
import com.example.dataflownav.ui.theme.DataFlowNavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DataFlowNavTheme {
                val navController = rememberNavController()
                AppNavGraph(navController)
            }
        }
    }
}

@Composable
fun AppNavGraph(navController: NavHostController)
{
    NavHost(
        navController = navController,
        startDestination = "forget_pass"
    ) {
        composable("forget_pass")
        {
            ForgetPassScreen(
                onNextClick = { email ->
                    navController.navigate("verify_code/$email")
                }
            )
        }

        composable("verify_code/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            VerifyCodeScreen(
                email = email,
                onNextClick = { navController.navigate("new_pass/$email") },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("new_pass/{email}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            CreateNewPassScreen(
                email = email,
                onNextClick = { pass ->
                    navController.navigate("confirm/$email/$pass")
                },
                onBackClick = { navController.popBackStack() }
            )
        }

        composable("confirm/{email}/{pass}") { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            val pass = backStackEntry.arguments?.getString("pass") ?: ""
            ConfirmScreen(
                email = email,
                pass = pass,
                onBackClick = { navController.popBackStack() },
                onFinishClick = {
                    // Ví dụ: quay lại màn hình đăng nhập sau khi hoàn tất
                    navController.navigate("forget_pass") {
                        popUpTo("forget_pass") { inclusive = true }
                    }
                }
            )
        }
    }
}
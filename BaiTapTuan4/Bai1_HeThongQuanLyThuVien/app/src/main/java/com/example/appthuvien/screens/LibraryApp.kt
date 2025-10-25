package com.example.appthuvien.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.appthuvien.model.Destination
import com.example.appthuvien.model.LibraryRepository
import com.example.appthuvien.ui.theme.*

@Composable
fun LibraryApp() {
    val navController = rememberNavController()
    val repository = remember { LibraryRepository() }
    val startDestination = Destination.MANAGE
    var selectedDestination by rememberSaveable { mutableIntStateOf(startDestination.ordinal) }

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = LightBlue,
                windowInsets = NavigationBarDefaults.windowInsets
            ) {
                Destination.entries.forEachIndexed { index, destination ->
                    NavigationBarItem(
                        selected = selectedDestination == index,
                        onClick = {
                            navController.navigate(destination.route)
                            selectedDestination = index
                        },
                        icon = {
                            Icon(
                                painter = painterResource(destination.iconRes),
                                contentDescription = destination.contentDescription,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        label = {
                            Text(
                                destination.label,
                                style = MaterialTheme.typography.titleSmall.copy(fontSize = 14.sp)
                            )
                        },

                        colors = NavigationBarItemDefaults.colors(
                            unselectedIconColor = WhiteOrange,
                            selectedIconColor = DarkBlue,
                            unselectedTextColor = WhiteOrange,
                            selectedTextColor = DarkBlue,
                            indicatorColor = WhiteBlue
                        )
                    )
                }
            }
        }
    ) { paddingValues ->
        AppNavHost(navController, repository, Modifier.padding(paddingValues))
    }
}

@Composable
fun AppNavHost(navController: androidx.navigation.NavHostController, repository: LibraryRepository, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Destination.MANAGE.route, modifier = modifier) {
        composable(Destination.MANAGE.route) {
            ManagementScreen(repository)
        }
        composable(Destination.BOOK.route) {
            BookListScreen(repository)
        }
        composable(Destination.STUDENT.route) {
            StudentListScreen(repository)
        }
    }
}

@Preview (showBackground = true)
@Composable
fun LibraryAppPreview()
{
    AppThuVienTheme {
        LibraryApp()
    }
}
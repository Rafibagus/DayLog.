package org.d3if0075.daylog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.d3if0075.daylog.ui.screen.LoadingScreen
import org.d3if0075.daylog.ui.screen.LoginScreen
import org.d3if0075.daylog.ui.screen.MainScreen
import org.d3if0075.daylog.ui.screen.RegisterScreen
import org.d3if0075.daylog.ui.screen.WelcomeScreen

@Composable
fun SetupNavGraph(navHostController: NavHostController = rememberNavController()) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Loading.route,
        ){
        composable(route = Screen.Loading.route){
            LoadingScreen(navHostController)
        }
        composable(route = Screen.Welcome.route){
            WelcomeScreen(navHostController)
        }
        composable(route = Screen.Login.route){
            LoginScreen(navHostController)
        }
        composable(route = Screen.Register.route){
            RegisterScreen(navHostController)
        }
        composable(route = Screen.Home.route){
            MainScreen(navHostController)
        }
    }
}
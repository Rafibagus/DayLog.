package org.d3if0075.daylog.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if0075.daylog.ui.screen.AboutDaylogScreen
import org.d3if0075.daylog.ui.screen.DetailScreen
import org.d3if0075.daylog.ui.screen.KEY_ID_DAYLOG
import org.d3if0075.daylog.ui.screen.LineChartScreen
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
        composable(route = Screen.Notes.route){
            DetailScreen(navHostController)
        }
        composable(
            route = Screen.FormUbah.route,
            arguments = listOf(
                navArgument(KEY_ID_DAYLOG){type = NavType.LongType}
            )
        ){
            navBackStackEntry ->
            val id = navBackStackEntry.arguments?.getLong(KEY_ID_DAYLOG)
            DetailScreen(navHostController, id)
        }
        composable(route = Screen.Chart.route){
            LineChartScreen(navHostController)
        }
        composable(route = Screen.About.route){
            AboutDaylogScreen(navHostController)
        }
    }
}
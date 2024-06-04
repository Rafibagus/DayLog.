package org.d3if0075.daylog.navigation

import AboutDaylogScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.d3if0075.daylog.ui.screen.DetailScreen
import org.d3if0075.daylog.ui.screen.KEY_ID_DAYLOG
import org.d3if0075.daylog.ui.screen.LoadingScreen
import org.d3if0075.daylog.ui.screen.LoginScreen
import org.d3if0075.daylog.ui.screen.MainScreen
import org.d3if0075.daylog.ui.screen.NewPwScreen
import org.d3if0075.daylog.ui.screen.PieChartScreen
import org.d3if0075.daylog.ui.screen.ProfileScreen
import org.d3if0075.daylog.ui.screen.RegisterScreen
import org.d3if0075.daylog.ui.screen.ResetScreen
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
        composable(
            route = Screen.Chart.route + "/{sad}/{disappointed}/{calm}/{happy}/{excited}",
            arguments = listOf(
                navArgument("sad") { type = NavType.IntType },
                navArgument("disappointed") { type = NavType.IntType },
                navArgument("calm") { type = NavType.IntType },
                navArgument("happy") { type = NavType.IntType },
                navArgument("excited") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val sad = backStackEntry.arguments?.getInt("sad") ?: 0
            val disappointed = backStackEntry.arguments?.getInt("disappointed") ?: 0
            val calm = backStackEntry.arguments?.getInt("calm") ?: 0
            val happy = backStackEntry.arguments?.getInt("happy") ?: 0
            val excited = backStackEntry.arguments?.getInt("excited") ?: 0

            PieChartScreen(navHostController, sad, disappointed, calm, happy, excited)
        }

        composable(route = Screen.About.route){
            AboutDaylogScreen(navHostController)
        }
        composable(route = Screen.Profile.route){
            ProfileScreen(navHostController)
        }
        composable(route = Screen.ResetPw.route) {
            ResetScreen(navHostController)
        }
        composable(
            route = Screen.NewPw.route,
            arguments = listOf(navArgument("userId") { type = NavType.LongType })
        ) { navBackStackEntry ->
            val userId = navBackStackEntry.arguments?.getLong("userId") ?: 0L
            NewPwScreen(userId, navHostController) }

    }
}
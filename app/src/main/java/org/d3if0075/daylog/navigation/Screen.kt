package org.d3if0075.daylog.navigation

sealed class Screen(val route: String) {
    data object Loading: Screen("loadingScreen")
    data object Welcome: Screen("welcomeScreen")
    data object Login: Screen("loginScreen")
    data object Register: Screen("registerScreen")
    data object Home: Screen("mainScreen")
    data object Notes: Screen("detailScreen")
}
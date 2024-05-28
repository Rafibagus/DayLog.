package org.d3if0075.daylog.navigation

import org.d3if0075.daylog.ui.screen.KEY_ID_DAYLOG

sealed class Screen(val route: String) {
    data object Loading: Screen("loadingScreen")
    data object Welcome: Screen("welcomeScreen")
    data object Login: Screen("loginScreen")
    data object Register: Screen("registerScreen")
    data object Home: Screen("mainScreen")
    data object Notes: Screen("detailScreen")
    data object FormUbah: Screen("detailScreen/{$KEY_ID_DAYLOG}"){
        fun withId(id: Long) = "detailScreen/$id"
    }
    data object Chart: Screen("pieChartScreen")

    data object About: Screen("aboutDaylogScreen")

    data object Profile: Screen("profileScreen")
}
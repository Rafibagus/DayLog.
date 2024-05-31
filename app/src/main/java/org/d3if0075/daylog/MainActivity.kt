package org.d3if0075.daylog

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import org.d3if0075.daylog.navigation.SetupNavGraph
import org.d3if0075.daylog.ui.theme.DayLogTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        installSplashScreen()

        setContent {
            DayLogTheme {
                MainActivityContent()
            }
        }
    }
}

@Composable
fun MainActivityContent() {  val navController = rememberNavController()
    SetupNavGraph(navHostController = navController)}
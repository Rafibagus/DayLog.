package org.d3if0075.daylog.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.d3if0075.daylog.R
import org.d3if0075.daylog.model.loadImage
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.navigation.Screen.Home.route
import org.d3if0075.daylog.ui.theme.DayLogTheme

@Composable
fun LoadingScreen(navHostController: NavHostController) {
    val backgroundImage = loadImage(R.drawable.background_daylog)
    val kaisade = FontFamily(Font(R.font.kaisei_decol_bold))
    val coroutineScope = rememberCoroutineScope()

    DisposableEffect(Unit) {
        coroutineScope.launch {
            delay(1200)
            navHostController.navigate(Screen.Welcome.route)
        }
        onDispose { }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            bitmap = backgroundImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.logo),
                fontSize = 36.sp,
                fontFamily = kaisade,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun LoadingPreview( ) {
    DayLogTheme {
        LoadingScreen(navHostController = rememberNavController())
    }
}
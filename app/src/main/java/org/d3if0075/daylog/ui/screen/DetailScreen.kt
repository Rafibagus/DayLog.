package org.d3if0075.daylog.ui.screen

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0075.daylog.R
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.ui.theme.DayLogTheme

@Composable
fun DetailScreen(navHostController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(onClick = {navHostController.popBackStack()}) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = stringResource(R.string.kembali),
                tint = Color.Black
            )
        }
        IconButton(onClick = {navHostController.navigate(Screen.Home.route)}) {
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = stringResource(R.string.kembali),
                tint = Color.Black
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
     DayLogTheme {
        DetailScreen(rememberNavController())
    }
}
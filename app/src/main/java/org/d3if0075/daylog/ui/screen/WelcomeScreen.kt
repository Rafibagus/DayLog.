package org.d3if0075.daylog.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0075.daylog.R
import org.d3if0075.daylog.model.loadImage
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.ui.theme.DarkBrown
import org.d3if0075.daylog.ui.theme.DayLogTheme
import org.d3if0075.daylog.ui.theme.Grey1

@Composable
fun WelcomeScreen(navHostController: NavHostController) {
    val backgroundImage = loadImage(R.drawable.background_daylog)
    val kaisade = FontFamily(Font(R.font.kaisei_decol_bold))

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
            Text(
                text = stringResource(R.string.selamat_datang),
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            MulaiButton(onClick = {navHostController.navigate(Screen.Login.route)})
        }
    }
}

@Composable
fun MulaiButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp),
        shape = MaterialTheme.shapes.extraSmall,
        colors = ButtonDefaults.buttonColors(DarkBrown)
    ) {
        Text(
            text = "Mulai",
            fontSize = 16.sp
        )
    }
}

@Composable
fun RegisterButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(Grey1)
    ) {
        Text(
            text = "Register",
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Composable
fun LoginButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(Grey1)
    ) {
        Text(
            text = "Login",
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Composable
fun VerifyButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.padding(8.dp),
        shape = MaterialTheme.shapes.medium,
        colors = ButtonDefaults.buttonColors(Grey1)
    ) {
        Text(
            text = "Veryfy",
            fontSize = 16.sp,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun WelcomePreview( ) {
    DayLogTheme {
        WelcomeScreen(navHostController = rememberNavController())
    }
}
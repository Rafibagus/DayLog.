package org.d3if0075.daylog.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if0075.daylog.R
import org.d3if0075.daylog.database.DaylogDb
import org.d3if0075.daylog.model.loadImage
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.ui.theme.DarkBrown
import org.d3if0075.daylog.ui.theme.DayLogTheme

@Composable
fun ResetScreen(navController: NavHostController) {
    val backgroundImage = loadImage(R.drawable.background_daylog)
    val kaisade = FontFamily(Font(R.font.kaisei_decol_bold))
    val email = remember { mutableStateOf("") }
    val context = LocalContext.current
    val userDao = DaylogDb.getInstance(context).dao

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
                modifier = Modifier.padding(bottom = 32.dp)
            )
            Column(
                modifier = Modifier
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.reset_pw),
                    fontSize = 20.sp,
                    modifier = Modifier
                )
                Spacer(modifier = Modifier.height(32.dp))

                OutlinedTextField(
                    value = email.value,
                    onValueChange = { email.value = it },
                    label = { Text(text = stringResource(id = R.string.email)) },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                Button(onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val user = userDao.getUserByEmail(email.value)
                        if (user != null) {
                            withContext(Dispatchers.Main) {
                                navController.navigate("new_pw_screen/${user.id}")
                            }
                        } else {
                            // Show error message
                        }
                    }
                },
                    modifier = Modifier.padding(8.dp),
                    shape = MaterialTheme.shapes.extraSmall,
                    colors = ButtonDefaults.buttonColors(DarkBrown)
                ) {
                    Text(text = stringResource(R.string.verify))
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = stringResource(R.string.register_to_login)
                )
                Text(
                    text = stringResource(R.string.Masuk),
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .clickable {
                            navController.navigate(Screen.Login.route)
                        },
                    color = Color.Blue
                )
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun ResetPwScreenPreview() {
    DayLogTheme {
        ResetScreen(navController = rememberNavController())
    }
}
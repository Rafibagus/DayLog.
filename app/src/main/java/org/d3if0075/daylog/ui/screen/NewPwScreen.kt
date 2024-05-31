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
import androidx.compose.ui.text.input.PasswordVisualTransformation
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
fun NewPwScreen(userId: Long, navController: NavHostController) {
    val backgroundImage = loadImage(R.drawable.background_daylog)
    val kaisade = FontFamily(Font(R.font.kaisei_decol_bold))
    val newpw = remember { mutableStateOf("") }
    val newconfirmpw = remember { mutableStateOf("") }
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
                modifier = Modifier.padding(bottom = 8.dp)
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
                OutlinedTextField(
                    value = newpw.value,
                    onValueChange = { newpw.value = it },
                    label = { Text("Enter New Password") },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = newconfirmpw.value,
                    onValueChange = { newconfirmpw.value = it },
                    label = { Text("Confirm New Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = MaterialTheme.typography.bodyLarge
                )

                Button(onClick = {
                    if (newpw.value == newconfirmpw.value) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val user = userDao.getUserById(userId)
                            if (user != null) {
                                userDao.update(user.copy(password = newpw.value))
                                withContext(Dispatchers.Main) {
                                    navController.navigate(Screen.Login.route) {
                                        popUpTo(Screen.NewPw.route) { inclusive = true }
                                    }
                                }
                            }
                        }
                    } else {
                        // Show error message
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
fun NewPwPreview() {
    DayLogTheme {
        NewPwScreen(userId = 1L, navController = rememberNavController())
    }
}
package org.d3if0075.daylog.ui.screen

import android.util.Patterns
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.launch
import org.d3if0075.daylog.R
import org.d3if0075.daylog.database.DaylogDb
import org.d3if0075.daylog.model.RegisterViewModel
import org.d3if0075.daylog.model.loadImage
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.ui.theme.DayLogTheme
import org.d3if0075.daylog.util.ViewModelFactory

@Composable
fun RegisterScreen(navHostController: NavHostController) {
    val backgroundImage = loadImage(R.drawable.background_daylog)
    val kaisade = FontFamily(Font(R.font.kaisei_decol_bold))

    val context = LocalContext.current
    val db = DaylogDb.getInstance(context)
    val factory = ViewModelFactory(db.dao)
    val viewModel: RegisterViewModel = viewModel(factory = factory)
    val coroutineScope = rememberCoroutineScope()

    var name by remember { mutableStateOf("") }
    var nameError by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var emailError by remember { mutableStateOf(false) }

    var enterpw by remember { mutableStateOf("") }
    var enterpwError by remember { mutableStateOf(false) }

    var confirmpw by remember { mutableStateOf("") }
    var confirmpwError by remember { mutableStateOf(false) }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

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
                    .padding(16.dp)
                    .background(Color.White, RoundedCornerShape(16.dp))
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(R.string.register),
                    fontSize = 20.sp,
                    modifier = Modifier
                )
                OutlinedTextField(
                    value = name,
                    onValueChange = { name = it },
                    label = { Text("Masukkan nama") },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Masukkan alamat surel") },
                    textStyle = MaterialTheme.typography.bodyLarge,
                    isError = emailError
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = enterpw,
                    onValueChange = { enterpw = it },
                    label = { Text("Masukkan kata sandi") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (passwordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility

                        IconButton(onClick = {
                            passwordVisible = !passwordVisible
                        }) {
                            Icon(imageVector = image, contentDescription = null)
                        }
                    },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmpw,
                    onValueChange = { confirmpw = it },
                    label = { Text("Konfirmasi kata sandi") },
                    visualTransformation = if (confirmPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val image = if (confirmPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility

                        IconButton(onClick = {
                            confirmPasswordVisible = !confirmPasswordVisible
                        }) {
                            Icon(imageVector = image, contentDescription = null)
                        }
                    },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                RegisterButton(
                    onClick = {
                        nameError = (name == "")
                        emailError = (email == "")
                        enterpwError = (enterpw.isEmpty())
                        confirmpwError = (confirmpw.isEmpty())
                        if (nameError || emailError || enterpwError || confirmpwError) {
                            Toast.makeText(
                                context, context.getString(R.string.input_invalid),
                                Toast.LENGTH_SHORT
                            ).show()
                            return@RegisterButton
                        }
                        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                            Toast.makeText(
                                context, context.getString(R.string.email_invalid),
                                Toast.LENGTH_SHORT
                            ).show()
                            return@RegisterButton
                        }
                        if (!email.contains("@gmail.com")) {
                            Toast.makeText(
                                context, "Email anda kurang tepat", // Pesan yang ingin ditampilkan
                                Toast.LENGTH_SHORT
                            ).show()
                            return@RegisterButton
                        }
                        if (enterpw.length < 8) {
                            Toast.makeText(
                                context, context.getString(R.string.password_invalid),
                                Toast.LENGTH_SHORT
                            ).show()
                            return@RegisterButton
                        }
                        if (confirmpw != enterpw) {
                            Toast.makeText(
                                context, context.getString(R.string.confirm_invalid),
                                Toast.LENGTH_SHORT
                            ).show()
                            return@RegisterButton
                        } else {
                            coroutineScope.launch {
                                if (viewModel.register(name, email, enterpw, confirmpw)) {
                                    Toast.makeText(
                                        context, context.getString(R.string.input_valid),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navHostController.navigate(Screen.Login.route)
                                } else {
                                    Toast.makeText(
                                        context, context.getString(R.string.registered_account),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                            }
                        }
                    }
                )

                Text(
                    text = stringResource(R.string.register_to_login)
                )
                ClickableText(
                    text = buildAnnotatedString {
                        withStyle(SpanStyle(color = Color.Blue)) {
                            append(stringResource(id = R.string.Masuk))
                        }
                    },
                    onClick = { navHostController.navigate(Screen.Login.route) },
                    modifier = Modifier
                        .padding(top = 8.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    DayLogTheme {
        RegisterScreen(navHostController = rememberNavController())
    }
}

package org.d3if0075.daylog.ui.screen

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
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
                    label = { Text("Enter name") },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))


                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Enter Email") },
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = enterpw,
                    onValueChange = { enterpw = it },
                    label = { Text("Enter Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = confirmpw,
                    onValueChange = { confirmpw = it },
                    label = { Text("Confirm Password") },
                    visualTransformation = PasswordVisualTransformation(),
                    textStyle = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(16.dp))

                RegisterButton(
                    onClick = {
                        nameError = (name == "")
                        emailError = (email == "")
                        enterpwError = (enterpw == "")
                        confirmpwError = (confirmpw == "")
                        if (nameError || emailError || enterpwError || confirmpwError){
                            Toast.makeText(
                                context, context.getString(R.string.input_invalid),
                                Toast.LENGTH_SHORT
                            ).show()
                            return@RegisterButton
                        } else {
                            coroutineScope.launch{
                                if (viewModel.register(name, email, enterpw, confirmpw)){
                                    Toast.makeText(
                                        context, context.getString(R.string.input_valid),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    navHostController.navigate(Screen.Login.route)
                                }
                                else{
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
                            append(stringResource(id = R.string.login))
                        }
                    },
                    onClick = {navHostController.navigate(Screen.Login.route)},
                    modifier = Modifier
                        .padding(top = 8.dp),
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview( ) {
    DayLogTheme {
        RegisterScreen(navHostController = rememberNavController())
    }
}
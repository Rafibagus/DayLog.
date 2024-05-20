package org.d3if0075.daylog.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0075.daylog.R
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.ui.theme.DayLogTheme
import org.d3if0075.daylog.ui.theme.Grey1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.Notes.route) // Asumsi Screen.FormBaru adalah screen yang ingin Anda navigasi
                },
                modifier = Modifier.padding(bottom = 70.dp),
                containerColor = Color(0xFFAB8172),
                shape = RoundedCornerShape(50.dp),
                contentColor = Color.White,
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = stringResource(R.string.tambah_catatan),
                    tint = Color.White
                )
            }
        }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(id = R.drawable.baseline_sentiment_very_satisfied_24),
                        contentDescription = null,
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column(
                        modifier = Modifier.padding(end = 16.dp)
                    ) {
                        Text(
                            text = "Hai,",
                            fontSize = 16.sp,
                            color = Color.Gray
                        )
                        Text(
                            text = "Agus",
                            fontSize = 20.sp,
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    }
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = { searchQuery = it },
                        placeholder = { Text(text = stringResource(R.string.search)) },
                        shape = RoundedCornerShape(50),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        trailingIcon = {
                            IconButton(onClick = {  }) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = stringResource(R.string.search),
                                    tint = Color.Black
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
//                            containerColor =  Color(red = 215f, green = 192f, blue = 174f, alpha = 0.3f),
                            focusedBorderColor = Color.Transparent,
                            unfocusedBorderColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .height(50.dp)
                    )
                }
                Text(
                    text = stringResource(R.string.opening),
                    fontSize = 16.sp,
                    color = Color.Black,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .size(500.dp, 68.dp)
                    .background(Grey1)
                    .align(Alignment.BottomCenter),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(50.dp)
                ) {
                    Image(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.home_house),
                        contentDescription = stringResource(id = R.string.home)
                    )
                    Image(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.analytics_graph_chart),
                        contentDescription = stringResource(id = R.string.graph)
                    )
                    Image(
                        modifier = Modifier
                            .size(32.dp)
                            .align(Alignment.CenterVertically),
                        painter = painterResource(id = R.drawable.account_user_person_square),
                        contentDescription = stringResource(id = R.string.account)
                    )
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun ScreenPreview() {
    DayLogTheme {
        MainScreen(navHostController = rememberNavController())
    }
}


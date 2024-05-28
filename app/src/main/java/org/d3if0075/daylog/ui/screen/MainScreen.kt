package org.d3if0075.daylog.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0075.daylog.R
import org.d3if0075.daylog.database.DaylogDb
import org.d3if0075.daylog.model.Catatan
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.ui.theme.DayLogTheme
import org.d3if0075.daylog.ui.theme.Grey1
import org.d3if0075.daylog.util.CatatanModelFactory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navHostController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }

    val context = LocalContext.current
    val db = DaylogDb.getInstance(context)
    val factory = CatatanModelFactory(db.catatanDao)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navHostController.navigate(Screen.Notes.route)
                },
                containerColor = Color(0xFFAB8172),
                modifier = Modifier.padding(bottom = 80.dp),
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
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(top = 14.dp, bottom = 20.dp)
                ) {
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
                            fontWeight = FontWeight.Bold
                        )
                    }
                    OutlinedTextField(
                        value = searchQuery,
                        onValueChange = {
                            searchQuery = it
                            viewModel.searchNotes(it) // Trigger search on value change
                        },
                        placeholder = { Text(text = stringResource(R.string.search)) },
                        shape = RoundedCornerShape(50),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                        trailingIcon = {
                            IconButton(onClick = {
                                viewModel.searchNotes(searchQuery) // Optional, as search is triggered on value change
                            }) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = stringResource(R.string.search),
                                    tint = Color.Black
                                )
                            }
                        },
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            containerColor =  Color(0xFFEEE3CB),
                            focusedBorderColor = Color(0xFFEEE3CB),
                            unfocusedBorderColor = Color(0xFFEEE3CB)
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

            if (data.isEmpty()) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = stringResource(id = R.string.list_kosong))
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(top = 200.dp, bottom = 84.dp)
                ) {
                    items(data) { catatan ->
                        ListItem(catatan = catatan) {
                            navHostController.navigate(Screen.FormUbah.withId(catatan.id))
                        }
                        Divider()
                    }
                }
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
                    Box(
                        modifier = Modifier.clickable {
                            // Handle home image click
                        }
                    ) {
                        Image(
                            modifier = Modifier.align(Alignment.Center),
                            painter = painterResource(id = R.drawable.home_house),
                            contentDescription = stringResource(id = R.string.home)
                        )
                    }

                    Box(
                        modifier = Modifier.clickable {
                            navHostController.navigate(Screen.Chart.route)
                            // Handle graph image click
                        }
                    ) {
                        Image(
                            modifier = Modifier.align(Alignment.Center),
                            painter = painterResource(id = R.drawable.analytics_graph_chart),
                            contentDescription = stringResource(id = R.string.graph)
                        )
                    }

                    Box(
                        modifier = Modifier.clickable {
                            navHostController.navigate(Screen.About.route)
                            // Handle account image click
                        }
                    ) {
                        Image(
                            modifier = Modifier.align(Alignment.Center),
                            painter = painterResource(id = R.drawable.account_user_person_square),
                            contentDescription = stringResource(id = R.string.account)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun ListItem(catatan: Catatan, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = catatan.judul,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = catatan.catatan,
                fontSize = 14.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
        IconButton(onClick = { /* TODO: Add more actions here if needed */ }) {
            Icon(imageVector = Icons.Filled.MoreVert, contentDescription = "More")
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



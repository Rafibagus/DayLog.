package org.d3if0075.daylog.ui.screen

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.d3if0075.daylog.R
import org.d3if0075.daylog.ui.theme.DayLogTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, id: Long? = null) {
    val viewModel: DetailViewModel = viewModel()

    var judul by remember { mutableStateOf("") }
    var catatan by remember { mutableStateOf("") }

    if (id != null) {
        val data = viewModel.getCatatan(id)
        judul = data.judul
        catatan = data.catatan
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.kembali),
                            tint = Color.Black
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(
                            text = stringResource(id = R.string.tambah_catatan),
                            color = Color.Black
                        )
                    else
                        Text(text = stringResource(id = R.string.edit_catatan))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFEEE3CB),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.simpan),
                            tint = Color.Black
                        )
                    }
                }
            )
        }
    ) { padding ->
        FormCatatan(
            title = judul,
            onTitleChange = { judul = it },
            desc = catatan,
            onDescChange = { catatan = it },
            modifier = Modifier.padding()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormCatatan(
    title: String, onTitleChange: (String) -> Unit,
    desc: String, onDescChange: (String) -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 80.dp, start = 16.dp, end = 16.dp),

        ) {
        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(R.string.judul)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier
                .fillMaxWidth(),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor =  Color(0xFFEEE3CB),
                focusedBorderColor = Color(0xFFEEE3CB),
                unfocusedBorderColor = Color(0xFFEEE3CB)
            )
        )
        //mood emoticon
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .clickable { }
                .border(
                    1.dp, Color(0xFFEEE3CB), shape = RoundedCornerShape(8.dp)
                ) // Added border around the row
                .padding(8.dp) // Add padding inside the border

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Align items horizontally to the center within the Column
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Bagaimana mood Anda hari ini?",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    // List of vector assets for moods
                    val moodIcons = listOf(
                        R.drawable.excited,
                        R.drawable.baseline_sentiment_satisfied_alt_24,
                        R.drawable.baseline_sentiment_very_satisfied_24,
                        R.drawable.baseline_sentiment_dissatisfied_24,
                        R.drawable.baseline_sentiment_very_dissatisfied_24,
                    )
                    val moodColors = listOf(
                        Color.Red,
                        Color.Gray,
                        Color.Green,
                        Color.Yellow,
                        Color.Blue
                    )
                    moodIcons.forEachIndexed { index, iconRes ->
                        Icon(
                            painter = painterResource(id = iconRes),
                            contentDescription = "Mood Icon",
                            tint = moodColors[index],
                            modifier = Modifier.size(60.dp) // Set the size of the icons
                        )
                    }
                }
            }
        }
        //isi catatan
        OutlinedTextField(
            value = desc,
            onValueChange = { onDescChange(it) },
            label = { Text(text = stringResource(R.string.isi_catatan)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            ),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color(0xFFEEE3CB),
                unfocusedBorderColor = Color(0xFFEEE3CB)
            ),
            modifier = modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            minLines = 20
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DetailScreenPreview() {
    DayLogTheme {
        DetailScreen(rememberNavController())
    }
}

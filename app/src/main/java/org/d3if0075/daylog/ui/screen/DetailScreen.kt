package org.d3if0075.daylog.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
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
import org.d3if0075.daylog.database.DaylogDb
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.ui.theme.DayLogTheme
import org.d3if0075.daylog.util.CatatanModelFactory

const val KEY_ID_DAYLOG = "idDaylog"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavController, id: Long? = null) {
    val context = LocalContext.current
    val db = DaylogDb.getInstance(context)
    val factory = CatatanModelFactory(db.catatanDao)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var judul by remember { mutableStateOf("") }
    var catatan by remember { mutableStateOf("") }
    var showMenu by remember { mutableStateOf(false) }
    var selectedMood by remember { mutableStateOf(-2) }

    LaunchedEffect(id) {
        if (id != null) {
            val data = viewModel.getCatatan(id)
            if (data != null) {
                judul = data.judul
                catatan = data.catatan
                selectedMood = data.mood // Ambil mood dari data
            }
        }
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
                    Text(
                        text = if (id == null) stringResource(id = R.string.tambah_catatan) else stringResource(id = R.string.edit_catatan),
                        color = Color.Black
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color(0xFFEEE3CB),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = { showMenu = !showMenu }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = stringResource(R.string.lainnya),
                            tint = Color.Black
                        )
                    }
                    DropdownMenu(
                        expanded = showMenu,
                        onDismissRequest = { showMenu = false },
                        modifier = Modifier.background(Color.White)
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                showMenu = false
                                if (id != null) {
                                    viewModel.delete(id)
                                    Toast.makeText(context, R.string.delete, Toast.LENGTH_LONG).show()
                                    navController.popBackStack()
                                }
                            },
                            text = {
                                Text(
                                    text = stringResource(R.string.delete),
                                    color = Color.Black
                                )
                            }
                        )
                    }
                    IconButton(onClick = {
                        if (judul.isBlank() || catatan.isBlank()) {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        if (id == null) {
                            viewModel.insert(judul, catatan, selectedMood) // Simpan mood yang dipilih
                        } else {
                            viewModel.update(id, judul, catatan, selectedMood) // Perbarui mood yang dipilih
                        }
                        navController.popBackStack()
                    }) {
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
            modifier = Modifier.padding(padding)
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
    // Variabel state untuk melacak mood yang dipilih
    var selectedMood by remember { mutableStateOf(-2) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding( start = 16.dp, end = 16.dp),
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
                containerColor = Color(0xFFEEE3CB),
                focusedBorderColor = Color(0xFFEEE3CB),
                unfocusedBorderColor = Color(0xFFEEE3CB)
            )
        )
        // Emotikon mood
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .border(
                    1.dp, Color(0xFFEEE3CB), shape = RoundedCornerShape(8.dp)
                ) // Menambahkan border di sekitar row
                .padding(8.dp) // Menambahkan padding di dalam border
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally, // Menyelaraskan item secara horizontal di tengah dalam Column
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Bagaimana mood Anda hari ini?",
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    // Daftar aset vektor untuk mood
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
                            tint = if (selectedMood == index) Color.Black else moodColors[index], // Mengubah tint berdasarkan pilihan
                            modifier = Modifier
                                .size(60.dp) // Mengatur ukuran ikon
                                .clickable {
                                    selectedMood = index // Memperbarui mood yang dipilih saat diklik
                                }
                        )
                    }
                }
            }
        }
        // Isi catatan
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

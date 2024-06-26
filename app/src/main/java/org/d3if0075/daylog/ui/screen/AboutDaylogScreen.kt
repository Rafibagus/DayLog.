import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0075.daylog.R
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.ui.theme.DayLogTheme
import org.d3if0075.daylog.ui.theme.Grey1
import android.app.Activity
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.lifecycle.viewmodel.compose.viewModel
import org.d3if0075.daylog.database.DaylogDb
import org.d3if0075.daylog.model.MainViewModel
import org.d3if0075.daylog.model.UserViewModel
import org.d3if0075.daylog.util.CatatanModelFactory
import org.d3if0075.daylog.util.ViewModelFactory

@Composable
fun AboutDaylogScreen(navHostController: NavHostController) {
    val context1 = LocalContext.current
    val db1 = DaylogDb.getInstance(context1)
    val factory1 = ViewModelFactory(db1.dao)
    val viewModel1: UserViewModel = viewModel(factory = factory1)
    val data1 by viewModel1.data.collectAsState()

    var username by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("agusf4@gmail.com") }
    var password by remember { mutableStateOf("password") }
    var isEditMode by rememberSaveable { mutableStateOf(false) }

    LaunchedEffect(data1) {
        if (data1.isNotEmpty()) {
            val user = data1[1] // Get the first user from the list
            username = user.userName
            email = user.email
            password = user.password
            }
        }

    // Mendapatkan Activity dari LocalContext
    val activity = (LocalContext.current as? Activity)

    val context = LocalContext.current
    val db = DaylogDb.getInstance(context)
    val factory = CatatanModelFactory(db.catatanDao)
    val viewModel: MainViewModel = viewModel(factory = factory)
    val data by viewModel.data.collectAsState()

    var sad by remember { mutableStateOf(0) }
    var disappointed by remember { mutableStateOf(0) }
    var calm by remember { mutableStateOf(0) }
    var happy by remember { mutableStateOf(0) }
    var excited by remember { mutableStateOf(0) }

    LaunchedEffect(data) {
        sad = data.count { it.mood == 3 }
        disappointed = data.count { it.mood == 4 }
        calm = data.count { it.mood == 2 }
        happy = data.count { it.mood == 1 }
        excited = data.count { it.mood == 0 }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color(0xFFF5E8D4))
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.White)
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .padding(32.dp)
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_sentiment_satisfied_alt_24), // Ganti dengan sumber gambar Anda
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .shadow(4.dp)
                    .padding(16.dp)
            ) {
                TextField(
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nama pengguna") },
                    singleLine = true,
                    enabled = isEditMode,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                TextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Alamat surel") },
                    singleLine = true,
                    enabled = false,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                TextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Kata sandi") },
                    singleLine = true,
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        if (isEditMode){

                        }
                        isEditMode = !isEditMode
                    },
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEE3CB))
                ) {
                    Text(
                        if (isEditMode) "Simpan" else "Ubah",
                        color = Color.Black
                    )
                }

                Button(
                    onClick = { navHostController.navigate(Screen.Login.route)}, // Menutup aplikasi saat tombol "Keluar" ditekan
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFEEE3CB)),
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .border(
                            width = 1.dp, // Thickness of the border
                            color = Color.Black, // Color of the border
                            shape = RoundedCornerShape(4.dp) // Shape of the border
                        )
                ) {
                    Text("Keluar", color = Color.Red)
                }
            }
        }
        IconButton(
            onClick = {navHostController.navigate(Screen.Profile.route)},
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Info,
                contentDescription = "Info",
                modifier = Modifier.size(32.dp)
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
                Box(
                    modifier = Modifier
                        .clickable {
                            navHostController.navigate(Screen.Home.route)
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
                    modifier = Modifier
                        .clickable {
                            navHostController.navigate(Screen.Chart.route + "/${sad}/${disappointed}/${calm}/${happy}/${excited}")
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
                    modifier = Modifier
                        .clickable {
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

@Preview(showBackground = true)
@Composable
fun AboutDaylogScreenPreview() {
    DayLogTheme {
        AboutDaylogScreen(navHostController = rememberNavController())
    }
}
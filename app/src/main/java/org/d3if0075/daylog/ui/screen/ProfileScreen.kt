package org.d3if0075.daylog.ui.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if0075.daylog.R
import org.d3if0075.daylog.navigation.Screen
import org.d3if0075.daylog.ui.theme.DayLogTheme
import org.d3if0075.daylog.ui.theme.Grey1

@Composable
fun ProfileScreen(navHostController: NavHostController) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.tentang_daylog),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.deskripsi_daylog),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            lineHeight = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.deskripsi_tim),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            lineHeight = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ProfileImage(imageResource = R.drawable.foto_rere)
            ProfileImage(imageResource = R.drawable.foto_rafi)
            ProfileImage(imageResource = R.drawable.foto_raen)
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.kebijakan_privasi),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.deksripsi_kebijakan_privasi),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            lineHeight = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.hubungi_kami),
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Bold),
            textAlign = TextAlign.Justify
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = stringResource(id = R.string.deskripsi_hubungi_kami),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            lineHeight = 16.sp
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.terima_kasih),
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,
            lineHeight = 16.sp
        )
    }
    Box(){
        Row(
            modifier = Modifier
                .size(500.dp, 65.dp)
                .background(Grey1)
                .align(Alignment.BottomCenter),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(50.dp)
            ) {
            }
        }
    }
}

@Composable
fun ProfileImage(imageResource: Int) {
    Image(
        painter = painterResource(id = imageResource),
        contentDescription = null,
        modifier = Modifier
            .size(80.dp)
            .clip(CircleShape),
        contentScale = ContentScale.Crop
    )
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    DayLogTheme {
        ProfileScreen(navHostController = rememberNavController())
    }
}
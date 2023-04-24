package cz.krutsche.songbook.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cz.krutsche.songbook.MR
import cz.krutsche.songbook.SongRepository
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Settings(navController: NavController) {
    val songRepository = koinInject<SongRepository>()
    val composableScope = rememberCoroutineScope()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                stringResource(MR.strings.settings_title.resourceId),
                style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 28.sp)
            )
        }, navigationIcon = {
            IconButton(onClick = {
                navController.navigateUp()
            }) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        })
    }) {
        Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    "Dark mode",
                    style = TextStyle(fontSize = 20.sp)
                )
                Switch(checked = false, onCheckedChange = {})
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    stringResource(MR.strings.chords.resourceId),
                    style = TextStyle(fontSize = 20.sp)
                )
                Switch(checked = false, onCheckedChange = {})
            }
            var loading by remember { mutableStateOf(false) }
            Button(onClick = {
                composableScope.launch {
                    loading = true
                    songRepository.fetchSongs()
                    loading = false
                }
            }, enabled = !loading) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = if (loading) Modifier.padding(start = 32.dp) else Modifier.padding(
                        horizontal = 32.dp
                    )
                ) {
                    Text(
                        stringResource(MR.strings.update_button.resourceId),
                        style = TextStyle(fontSize = 24.sp)
                    )
                    if (loading)
                        CircularProgressIndicator(
                            modifier = Modifier
                                .padding(start = 12.dp)
                                .size(20.dp)
                        )
                }
            }
        }
    }
}
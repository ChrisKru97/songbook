package cz.krutsche.songbook.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cz.krutsche.songbook.SettingsRepository
import cz.krutsche.songbook.components.FavoriteButton
import cz.krutsche.songbook.components.song.SongBody
import cz.krutsche.songbook.SongRepository
import org.koin.compose.koinInject

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun Song(navController: NavController, songNumber: Int?) {
    val songRepository = koinInject<SongRepository>()

    if (songNumber == null) {
        return CircularProgressIndicator()
    }

    val song = songRepository.getSong(songNumber).collectAsState(initial = null).value
        ?: return CircularProgressIndicator()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text("${song.number}. ${song.name}")
            }, actions = {
                FavoriteButton(
                    number = song.number,
                    favorite = song.favorite != null && song.favorite != 0.toShort(),
                    defaultColor = MaterialTheme.colors.surface,
                    modifier = Modifier.padding(end = 8.dp)
                )
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            })
        },
    ) {
        val showChords = koinInject<SettingsRepository>().showChords
        SongBody(if (showChords && song.withChords != null) song.withChords as String else song.withoutChords)
    }
}
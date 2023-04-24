package cz.krutsche.songbook.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.krutsche.songbook.components.FavoriteButton
import cz.krutsche.songbook.components.song.SongBody
import cz.krutsche.songbook.SongRepository
import org.koin.compose.koinInject

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun Song(songNumber: Long?) {
    val songRepository = koinInject<SongRepository>()

    if (songNumber == null) {
        return CircularProgressIndicator()
    }

    val song = songRepository.getSong(songNumber).collectAsState(initial = null).value
        ?: return CircularProgressIndicator()

    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(
                    song.name,
                    style = TextStyle(
                        fontWeight = FontWeight.SemiBold,
                        fontSize = if (song.name.length > 50) 20.sp else if (song.name.length > 40) 24.sp else 28.sp
                    )
                )
            }, actions = {
                FavoriteButton(
                    number = song.number,
                    favorite = song.favorite != null && song.favorite != 0.toShort(),
                    defaultColor = Color.White,
                    modifier = Modifier.padding(end = 8.dp)
                )
            })
        },
    ) {
        SongBody(song.withoutChords)
    }
}
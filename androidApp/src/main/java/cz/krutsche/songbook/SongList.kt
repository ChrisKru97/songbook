package cz.krutsche.songbook

import cz.krutsche.songbook.sqldelight.Song as DbSong
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.compose.koinInject

@Composable
fun SongList() {
//    val listState = rememberLazyListState()
    val songRepository = koinInject<SongRepository>()
    val songsState = songRepository.listSongs().collectAsState(initial = listOf())
    val songs = songsState.value

    if (songs.isEmpty())
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }
    else
        LazyColumn {
            items(songs) {
                SongItem(it)
            }
        }
}

@Composable
fun SongItem(song: DbSong) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(Color.White)
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row {
            val textStyle = TextStyle(
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
            )
            Text("${song.number}.", modifier = Modifier.width(44.dp), style = textStyle)
            Text(song.name, style = textStyle)
        }
        val favorite = song.favorite != null && song.favorite != 0.toShort()
        val songRepository = koinInject<SongRepository>()
        Icon(
            if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            "favorite",
            modifier = Modifier
                .size(30.dp)
                .clickable { songRepository.setFavorite(song.number, !favorite) },
            tint = if (favorite) Color.Red else Color.Black
        )
    }
}
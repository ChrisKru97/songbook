package cz.krutsche.songbook

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SongList(songs: List<Song>) {
    LazyColumn {
        items(songs) {
            SongItem(it)
        }
    }
}

@Composable
fun SongItem(song: Song) {
    val isFavorite = song.number == 2
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
        Icon(
            if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
            "favorite",
            modifier = Modifier.size(30.dp),
            tint = if (isFavorite) Color.Red else Color.Black
        )
    }
}

@Preview
@Composable
fun SongListPreview() {
    val songs = listOf(
        Song(number = 1, name = "At pozehnan je Buh", withoutChords = ""),
        Song(number = 2, name = "At Tva ziva voda", withoutChords = ""),
        Song(number = 200, name = "At Tva ziva voda", withoutChords = "")
    )
    SongList(songs)
}

@Preview
@Composable
fun SongItemPreview() {
    val song = Song(number = 1, name = "At pozehnan je Buh", withoutChords = "")
    SongItem(song)
}
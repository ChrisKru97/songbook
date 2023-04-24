package cz.krutsche.songbook.components.song

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cz.krutsche.songbook.components.FavoriteButton
import cz.krutsche.songbook.sqldelight.Song

@Composable
fun SongItem(song: Song, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(1f)
            .background(Color.White)
            .clickable(onClick = onClick)
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
        FavoriteButton(
            number = song.number,
            favorite = song.favorite != null && song.favorite != 0.toShort()
        )
    }
}
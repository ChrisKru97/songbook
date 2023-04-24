package cz.krutsche.songbook.components.song

import android.os.Build
import androidx.annotation.RequiresApi
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
import cz.krutsche.songbook.sqldelight.List
import cz.krutsche.songbook.sqldelight.Song
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.format.FormatStyle
import java.util.Date

@Composable
fun GenericItem(number: Int, name: String, onClick: () -> Unit, trailing: @Composable () -> Unit) {
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
            Text("${number}.", modifier = Modifier.width(44.dp), style = textStyle)
            Text(name, style = textStyle)
        }
        trailing()
    }
}

@Composable
fun SongItem(song: Song, onClick: () -> Unit) {
    GenericItem(song.number, song.name, onClick) {
        FavoriteButton(
            number = song.number,
            favorite = song.favorite != null && song.favorite != 0.toShort()
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
val formatter: DateTimeFormatter =
    DateTimeFormatter.ofPattern("d.M. H:mm").withZone(ZoneId.systemDefault())

@Composable
fun HistoryItem(history: List, onClick: () -> Unit) {
    GenericItem(history.number, history.name, onClick) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Text(
                formatter.format(Instant.ofEpochSecond(history.dateTime)),
                style = TextStyle(fontSize = 12.sp)
            )
        }
    }
}
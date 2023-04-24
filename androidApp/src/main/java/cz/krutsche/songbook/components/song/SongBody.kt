package cz.krutsche.songbook.components.song

import androidx.compose.foundation.gestures.rememberTransformableState
import androidx.compose.foundation.gestures.transformable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SongBody(songText: String) {
    var fontSize by remember { mutableStateOf(20.sp) }
    val state = rememberTransformableState { zoom, _, _ ->
        fontSize *= zoom
    }
    Box(
        modifier = Modifier
            .padding(8.dp)
            .transformable(state = state)
            .fillMaxSize()
    ) {
        Text(
            songText,
            style = TextStyle(fontSize = fontSize)
        )
    }
}
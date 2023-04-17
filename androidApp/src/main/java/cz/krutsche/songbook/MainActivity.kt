package cz.krutsche.songbook

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    var songs by remember { mutableStateOf(listOf<Song>()) }
                    LaunchedEffect(true) {
                        songs = Greeting().getSongs()
                    }
                    Scaffold(
                        topBar = { TopAppBar(title = { Text("Worship songs") }) },
                        bottomBar = { BottomBar() }
                    ) {
                        if (songs.isEmpty()) CircularProgressIndicator()
                        else SongList(songs = songs)
                    }
                }
            }
        }
    }
}

package cz.krutsche.songbook.components.song

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cz.krutsche.songbook.MR
import cz.krutsche.songbook.SongRepository
import org.koin.compose.koinInject

enum class ListType {
    All,
    Search,
    Favorite,
//    History
}

@Composable
fun SongList(navController: NavController, listType: ListType = ListType.All) {
    val songRepository = koinInject<SongRepository>()
    val songs = when (listType) {
        ListType.All -> songRepository.listSongs().collectAsState(initial = listOf()).value
        ListType.Favorite -> songRepository.listFavorites().collectAsState(initial = listOf()).value
        ListType.Search -> songRepository.listSongs().collectAsState(initial = listOf()).value
    }

    if (songs.isEmpty())
        Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
            if (listType == ListType.All)
                CircularProgressIndicator()
            else
                Text(
                    stringResource(MR.strings.no_songs_info.resourceId),
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.SemiBold)
                )
        }
    else
        LazyColumn {
            items(songs) {
                SongItem(it) {
                    navController.navigate("Song/${it.number}") {
                        launchSingleTop = true
                    }
                }
            }
        }
}
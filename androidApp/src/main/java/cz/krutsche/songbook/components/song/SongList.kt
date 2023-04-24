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
import cz.krutsche.songbook.HistoryRepository
import cz.krutsche.songbook.MR
import cz.krutsche.songbook.SearchRepository
import cz.krutsche.songbook.SongRepository
import cz.krutsche.songbook.sqldelight.Song
import kotlinx.coroutines.flow.flowOf
import cz.krutsche.songbook.sqldelight.List as DbList
import org.koin.compose.koinInject

enum class ListType {
    All,
    Search,
    Favorite,
    History
}

@Composable
fun SongList(navController: NavController, listType: ListType = ListType.All) {
    val historyRepository = koinInject<HistoryRepository>()

    val songs = when (listType) {
        ListType.All -> koinInject<SongRepository>().listSongs()
            .collectAsState(initial = listOf()).value

        ListType.Favorite -> koinInject<SongRepository>().listFavorites()
            .collectAsState(initial = listOf()).value

        ListType.Search -> koinInject<SearchRepository>().listSearchedSongs()
            .collectAsState(initial = flowOf(listOf())).value.collectAsState(initial = listOf()).value

        ListType.History -> historyRepository.listSongs()
            .collectAsState(initial = listOf()).value
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
        LazyColumn(reverseLayout = listType == ListType.History) {
            items(songs) {
                val number = when (it) {
                    is Song -> it.number
                    is DbList -> it.number
                    else -> 1
                }
                val onClick = {
                    historyRepository.addToHistory(number)
                    navController.navigate("Song/${number}") {
                        launchSingleTop = true
                    }
                }
                when (it) {
                    is DbList -> HistoryItem(it, onClick)
                    is Song -> SongItem(it, onClick)
                }
            }
        }
}
package cz.krutsche.songbook.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import cz.krutsche.songbook.components.song.ListType
import cz.krutsche.songbook.components.song.SongList

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun Favorites(navController: NavController) =
    Scaffold(
        topBar = { TopAppBar(title = { Text("Favorite songs") }) },
    ) {
        SongList(navController, ListType.Favorite)
    }
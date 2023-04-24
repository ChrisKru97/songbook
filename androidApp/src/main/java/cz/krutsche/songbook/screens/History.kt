package cz.krutsche.songbook.screens

import android.annotation.SuppressLint
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import cz.krutsche.songbook.MR
import cz.krutsche.songbook.components.song.ListType
import cz.krutsche.songbook.components.song.SongList

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun History(navController: NavController) =
    Scaffold(
        topBar = {
            TopAppBar(title = {
                Text(stringResource(MR.strings.history_title.resourceId))
            }, navigationIcon = {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            })
        },
    ) {
        SongList(navController, ListType.History)
    }
package cz.krutsche.songbook.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cz.krutsche.songbook.MR
import cz.krutsche.songbook.components.BottomBar
import cz.krutsche.songbook.components.song.SongList

@Composable
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
fun Home(navController: NavController) {
    var searchBarShown by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            Surface(elevation = 8.dp) {
                Column(
                    Modifier
                        .background(MaterialTheme.colors.primary)
                        .padding(horizontal = 8.dp, vertical = if (searchBarShown) 8.dp else 12.dp)
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(
                        stringResource(MR.strings.app_title.resourceId),
                        style = TextStyle(color = Color.White, fontSize = 28.sp),
                    )
                    if (searchBarShown) {
                        val focusRequester = remember { FocusRequester() }
                        var searchValue by remember { mutableStateOf("") }
                        OutlinedTextField(
                            value = searchValue,
                            onValueChange = {
                                searchValue = it

                            },
                            leadingIcon = {
                                Icon(
                                    Icons.Default.Search,
                                    contentDescription = null
                                )
                            },
                            modifier = Modifier
                                .fillMaxWidth(1f)
                                .focusRequester(focusRequester),
                            shape = RoundedCornerShape(8.dp),
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                backgroundColor = Color.White,
                                focusedBorderColor = Color.Transparent,
                            )
                        )

                        LaunchedEffect(searchBarShown) {
                            if (searchBarShown) {
                                focusRequester.requestFocus()
                            }
                        }
                    }
                }
            }
        },
        bottomBar = { BottomBar(navController) { searchBarShown = !searchBarShown } }
    ) {
        SongList(navController)
    }
}
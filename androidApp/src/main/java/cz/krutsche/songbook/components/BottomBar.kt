package cz.krutsche.songbook.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import cz.krutsche.songbook.MR
import cz.krutsche.songbook.R
import cz.krutsche.songbook.SongRepository
import org.koin.compose.koinInject

@Composable
fun BottomBar(navController: NavController, toggleSearch: () -> Unit) {
    Row(
        modifier = Modifier
            .background(Color.Black.copy(0.3f))
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        BottomBarItem(
            icon = Icons.Default.Favorite,
            description = "Favorites",
            tint = Color.Red
        ) { navController.navigate("Favorites") }
        BottomBarItem(icon = Icons.Default.Search, description = "Search", onClick = toggleSearch)
        KeyboardItem(navController)
        BottomBarItem(
            localIcon = R.drawable.history,
            description = "History"
        ) { navController.navigate("History") }
        BottomBarItem(
            icon = Icons.Default.Settings,
            description = "Settings"
        ) { navController.navigate("Settings") }
    }
}

@Composable
fun BottomBarItem(
    icon: ImageVector? = null,
    localIcon: Int? = null,
    description: String,
    tint: Color = Color.Black,
    onClick: () -> Unit
) {
    if (icon == null && localIcon == null) return
    val interactionSource = remember { MutableInteractionSource() }
    val modifier = Modifier.size(28.dp)
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.White)
            .padding(8.dp)
            .clickable(onClick = onClick, interactionSource = interactionSource, indication = null)
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = description,
                modifier = modifier,
                tint = tint
            )
        }
        localIcon?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = description,
                modifier = modifier,
                tint = tint
            )
        }
    }
}

@Composable
fun KeyboardItem(navController: NavController) {
    var dialogShown by remember { mutableStateOf(false) }
    val songCount = koinInject<SongRepository>().songCount
    BottomBarItem(localIcon = R.drawable.keyboard, description = "Number") {
        if (songCount != null && songCount > 0)
            dialogShown = true
    }
    if (dialogShown) {
        var inputValue by remember { mutableStateOf<Int?>(null) }
        val focusRequester = remember { FocusRequester() }

        LaunchedEffect(true) {
            focusRequester.requestFocus()
        }

        val onConfirm = {
            dialogShown = false
            navController.navigate("Song/$inputValue")
        }

        AlertDialog(
            onDismissRequest = { dialogShown = false },
            confirmButton = {
                Button(onConfirm) { Text(stringResource(MR.strings.button_open.resourceId)) }
            },
            dismissButton = {
                Button({
                    dialogShown = false
                }) { Text(stringResource(MR.strings.button_close.resourceId)) }
            },
            title = {
                Text(
                    stringResource(MR.strings.song_number_input.resourceId),
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
                )
            },
            text = {
                OutlinedTextField(
                    value = if (inputValue != null) inputValue.toString() else "",
                    onValueChange = {
                        val numValue = it.toIntOrNull()
                        if (numValue != null && numValue != 0 && songCount != null && numValue <= songCount)
                            inputValue = numValue
                    },
                    modifier = Modifier
                        .focusRequester(focusRequester),
                    keyboardOptions = KeyboardOptions(
                        autoCorrect = false,
                        keyboardType = KeyboardType.Number,
                        imeAction = ImeAction.Done
                    ),
                    keyboardActions = KeyboardActions(onDone = { onConfirm() })
                )
            })
    }
}
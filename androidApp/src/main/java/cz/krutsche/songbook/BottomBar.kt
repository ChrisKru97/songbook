package cz.krutsche.songbook

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar() {
    Row(
        modifier = Modifier
            .background(Color.Black.copy(0.3f))
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        val modifier = Modifier.size(28.dp)
        BottomBarItem({
            Icon(
                Icons.Default.Favorite,
                contentDescription = "Favorites",
                modifier = modifier,
                tint = Color.Red
            )
        }) {

        }
        BottomBarItem({
            Icon(
                Icons.Default.Search,
                contentDescription = "Search",
                modifier = modifier,
            )
        }) {

        }
        BottomBarItem({
            Icon(
                Icons.Default.Search,
                contentDescription = "Number",
                modifier = modifier,
            )
        }) {

        }
        BottomBarItem({
            Icon(
                Icons.Default.Settings,
                contentDescription = "Settings",
                modifier = modifier,
            )
        }) {

        }
        BottomBarItem({
            Icon(
                Icons.Outlined.Send,
                contentDescription = "History",
                modifier = modifier,
            )
        }) {

        }
    }
}

@Composable
fun BottomBarItem(icon: @Composable () -> Unit, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(Color.White)
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        icon()
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar()
}
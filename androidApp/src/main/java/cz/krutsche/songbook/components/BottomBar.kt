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
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import cz.krutsche.songbook.R

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
        BottomBarItem(localIcon = R.drawable.keyboard, description = "Number") {
            // todo later
        }
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
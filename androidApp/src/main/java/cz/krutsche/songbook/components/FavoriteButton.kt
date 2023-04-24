package cz.krutsche.songbook.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cz.krutsche.songbook.SongRepository
import org.koin.compose.koinInject

@Composable
fun FavoriteButton(
    modifier: Modifier = Modifier,
    number: Int,
    favorite: Boolean,
    defaultColor: Color = Color.Black,
) {
    val songRepository = koinInject<SongRepository>()
    val interactionSource = remember { MutableInteractionSource() }

    Icon(
        if (favorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
        "favorite",
        modifier = modifier
            .size(30.dp)
            .clickable(interactionSource = interactionSource, indication = null) {
                songRepository.setFavorite(
                    number,
                    !favorite
                )
            },
        tint = if (favorite) Color.Red else defaultColor
    )
}
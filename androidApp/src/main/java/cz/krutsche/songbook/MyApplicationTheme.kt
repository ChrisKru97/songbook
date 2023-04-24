package cz.krutsche.songbook

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        darkColors(
            primary = Color(0xFF00B38F),
            primaryVariant = Color(0xFF00ABEE),
            secondary = Color(0xFF32DA03)
        )
    } else {
        lightColors(
            primary = Color(0xFF00ABEE),
            primaryVariant = Color(0xFF00B38F),
            secondary = Color(0xFF32DA03),
        )
    }

    MaterialTheme(
        colors = colors,
        content = content,
    )
}

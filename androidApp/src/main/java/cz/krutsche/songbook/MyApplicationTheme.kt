package cz.krutsche.songbook

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import org.koin.compose.koinInject

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    val modeSettings =
        koinInject<SettingsRepository>().theme.collectAsState(initial = Theme.Auto).value
    val darkTheme =
        modeSettings == Theme.Dark || (modeSettings == Theme.Auto && isSystemInDarkTheme())
    val colors = if (darkTheme) {
        darkColors()
    } else {
        lightColors()
    }

    MaterialTheme(
        colors = colors,
        content = content,
    )
}

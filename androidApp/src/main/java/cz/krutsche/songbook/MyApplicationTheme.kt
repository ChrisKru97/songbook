package cz.krutsche.songbook

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Color
import org.koin.compose.koinInject

private val LightColors = lightColors(
    primary = Color(0xFF00639A),
    onPrimary = Color(0xFFFFFFFF),
    error = Color(0xFFBA1A1A),
    onError = Color(0xFFFFFFFF),
    background = Color(0xFFFCFCFF),
    onBackground = Color(0xFF1A1C1E),
    surface = Color(0xFFFCFCFF),
    onSurface = Color(0xFF1A1C1E),
)


private val DarkColors = darkColors(
    primary = Color(0xFF95CCFF),
    onPrimary = Color(0xFF003352),
    error = Color(0xFFFFB4AB),
    onError = Color(0xFF690005),
    background = Color(0xFF1A1C1E),
    onBackground = Color(0xFFE2E2E5),
    surface = Color(0xFF1A1C1E),
    onSurface = Color(0xFFE2E2E5),
)

@Composable
fun MyApplicationTheme(
    content: @Composable () -> Unit
) {
    val modeSettings =
        koinInject<SettingsRepository>().theme.collectAsState(initial = Theme.Auto).value
    val darkTheme =
        modeSettings == Theme.Dark || (modeSettings == Theme.Auto && isSystemInDarkTheme())

    val colors = if (darkTheme) DarkColors else LightColors

    MaterialTheme(
        colors = colors,
        content = content,
    )
}

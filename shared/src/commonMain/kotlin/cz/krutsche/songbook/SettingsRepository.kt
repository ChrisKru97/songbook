package cz.krutsche.songbook

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getStringFlow
import com.russhwolf.settings.coroutines.getStringOrNullFlow
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

interface SettingsRepository {
    val fontSize: Int
    fun setFontSize(fontSize: Int)
    val showChords: Boolean
    fun setShowChords(showChords: Boolean)
    val textAlignment: TextAlignment
    fun setTextAlignment(textAlignment: TextAlignment)
    val theme: Flow<Theme>
    fun setTheme(theme: Theme)
    val language: Flow<String?>
    fun getLanguage(default: String): String
    fun setLanguage(language: String)
}

const val FONT_SIZE_KEY = "@fontSize"
const val TEXT_ALIGNMENT_KEY = "@textAlignment"
const val SHOW_CHORDS_KEY = "@showChords"
const val THEME_KEY = "@theme"
const val LANGUAGE_KEY = "@language"

enum class TextAlignment {
    Left, Center
}

enum class Theme {
    Dark, Light, Auto
}

val AvailableLangs = listOf("cs", "en", "pl")

@OptIn(ExperimentalSettingsApi::class)
class SettingsRepositoryImpl(val settings: ObservableSettings) : SettingsRepository {

    override val fontSize get() = settings.getInt(FONT_SIZE_KEY, 20)

    override fun setFontSize(fontSize: Int) =
        settings.set(FONT_SIZE_KEY, fontSize)

    override val showChords get() = settings.getBoolean(SHOW_CHORDS_KEY, false)

    override fun setShowChords(showChords: Boolean) =
        settings.set(SHOW_CHORDS_KEY, showChords)

    override val textAlignment
        get() = TextAlignment.valueOf(
            settings.getString(
                TEXT_ALIGNMENT_KEY,
                "Left"
            )
        )

    override fun setTextAlignment(textAlignment: TextAlignment) =
        settings.set(TEXT_ALIGNMENT_KEY, textAlignment.name)

    override val theme = settings.getStringFlow(THEME_KEY, "Auto").map { Theme.valueOf(it) }

    override fun setTheme(theme: Theme) =
        settings.set(THEME_KEY, theme.name)

    override val language = settings.getStringOrNullFlow(LANGUAGE_KEY)
    override fun getLanguage(default: String) =
        settings.getString(LANGUAGE_KEY, if (AvailableLangs.contains(default)) default else "en")

    override fun setLanguage(language: String) =
        settings.set(LANGUAGE_KEY, language)
}
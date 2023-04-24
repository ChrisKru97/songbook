package cz.krutsche.songbook

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

interface SettingsRepository {
    val fontSize: Int
    fun setFontSize(fontSize: Int)
    val showChords: Boolean
    fun setShowChords(showChords: Boolean)
    val textAlignment: TextAlignment
    fun setTextAlignment(textAlignment: TextAlignment)
    val theme: Theme
    fun setTheme(theme: Theme)
    fun getLanguage(default: String): String
    val language: String?
    fun setLanguage(language: String)
}

const val FONT_SIZE_KEY = "@fontSize"
const val TEXT_ALIGNMENT_KEY = "@textAlignment"
const val SHOW_CHORDS_KEY = "@showChords"
const val THEME_KEY = "@theme"
const val LANGUAGE_KEY = "@language"

enum class TextAlignment {
    Left, Right
}

enum class Theme {
    Dark, Light, Auto
}

val AvailableLangs = listOf("cs", "en", "pl")

class SettingsRepositoryImpl : SettingsRepository {
    val settings: Settings = Settings()

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

    override val theme get() = Theme.valueOf(settings.getString(THEME_KEY, "Auto"))

    override fun setTheme(theme: Theme) =
        settings.set(TEXT_ALIGNMENT_KEY, textAlignment.name)

    override fun getLanguage(default: String) =
        settings.getString(LANGUAGE_KEY, if (AvailableLangs.contains(default)) default else "en")

    override val language get() = settings.getStringOrNull(LANGUAGE_KEY)
    override fun setLanguage(language: String) =
        settings.set(LANGUAGE_KEY, textAlignment.name)
}
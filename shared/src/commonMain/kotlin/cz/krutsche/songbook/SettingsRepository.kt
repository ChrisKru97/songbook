package cz.krutsche.songbook

interface SettingsRepository {
    fun setFontSize(fontSize: Int)
    fun setTextAlignment(textAlignment: String)
    fun setDarkMode(darkMode: String)
    fun setLanguage(language: String)
}

class SettingsRepositoryImpl : SettingsRepository {
//    val settings: Settings = SettingsFactory().createSettings()

    override fun setFontSize(fontSize: Int) {
        TODO("Not yet implemented")
    }

    override fun setTextAlignment(textAlignment: String) {
        TODO("Not yet implemented")
    }

    override fun setDarkMode(darkMode: String) {
        TODO("Not yet implemented")
    }

    override fun setLanguage(language: String) {
        TODO("Not yet implemented")
    }
}
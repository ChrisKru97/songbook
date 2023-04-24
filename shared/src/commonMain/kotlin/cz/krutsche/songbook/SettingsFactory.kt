package cz.krutsche.songbook

import com.russhwolf.settings.ObservableSettings

expect class SettingsFactory {
    fun createSettings(): ObservableSettings
}
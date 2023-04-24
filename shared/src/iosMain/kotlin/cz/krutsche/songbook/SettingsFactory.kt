package cz.krutsche.songbook

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings

actual class SettingsFactory {
    actual fun createSettings(): ObservableSettings =
        NSUserDefaultsSettings.Factory().create()
}
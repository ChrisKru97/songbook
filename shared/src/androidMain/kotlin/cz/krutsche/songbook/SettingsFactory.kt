package cz.krutsche.songbook

import android.content.Context
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.SharedPreferencesSettings

actual class SettingsFactory(private val context: Context) {
    actual fun createSettings(): ObservableSettings =
        SharedPreferencesSettings.Factory(context).create()
}
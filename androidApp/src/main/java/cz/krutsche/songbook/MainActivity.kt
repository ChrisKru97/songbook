package cz.krutsche.songbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.Locale

class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val driverFactory = DriverFactory(context = baseContext)
        val settingsFactory = SettingsFactory(context = baseContext)
        val koin = initKoin(driverFactory, settingsFactory)

        GlobalScope.async {
            koin.get<SettingsRepository>().language.collect {
                if (it != null) {
                    try {
                        val locale = Locale(it)
                        Locale.setDefault(locale)
                        resources.configuration.setLocale(locale)
                        resources.updateConfiguration(
                            resources.configuration,
                            resources.displayMetrics
                        )
                        setTitle(MR.strings.app_title.resourceId)
                    } catch (_: Exception) {
                    }
                }
            }
        }

        setContent {
            MyApplicationTheme {
                MyNavHost()
            }
        }
    }
}

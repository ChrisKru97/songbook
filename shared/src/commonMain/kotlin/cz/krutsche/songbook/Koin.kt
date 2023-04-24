package cz.krutsche.songbook

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun appModule(driverFactory: DriverFactory, settingsFactory: SettingsFactory) = module {
    single {
        val driver = driverFactory.createDriver()
        Database(driver)
    }
    single<SongRepository> { SongRepositoryImpl(get()) }
    single<SettingsRepository> {
        val settings = settingsFactory.createSettings()
        SettingsRepositoryImpl(settings)
    }
    single<HistoryRepository> { HistoryRepositoryImpl(get()) }
    single<SearchRepository> { SearchRepositoryImpl(get()) }
}

@OptIn(DelicateCoroutinesApi::class)
fun initKoin(driverFactory: DriverFactory, settingsFactory: SettingsFactory): Koin {
    val koinApp = startKoin {
        modules(appModule(driverFactory, settingsFactory))
    }.koin

    GlobalScope.async {
        koinApp.get<SongRepository>().initialize()
    }

    return koinApp
}
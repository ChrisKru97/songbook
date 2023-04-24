package cz.krutsche.songbook

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun appModule(driverFactory: DriverFactory) = module {
    single {
        val driver = driverFactory.createDriver()
        Database(driver)
    }
    single<SongRepository> { SongRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl() }
}

@OptIn(DelicateCoroutinesApi::class)
fun initKoin(driverFactory: DriverFactory) {
    val koinApp = startKoin {
        modules(appModule(driverFactory))
    }.koin

    GlobalScope.async {
        koinApp.get<SongRepository>().initialize()
    }
}
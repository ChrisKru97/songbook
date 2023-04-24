package cz.krutsche.songbook

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val driverFactory = DriverFactory(context = baseContext)
        initKoin(driverFactory)
        this.setTitle(MR.strings.app_title.resourceId)
        setContent {
            MyApplicationTheme {
                MyNavHost()
            }
        }
    }
}

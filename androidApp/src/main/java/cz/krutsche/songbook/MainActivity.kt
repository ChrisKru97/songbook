package cz.krutsche.songbook

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val driverFactory = DriverFactory(context = baseContext)
        initKoin(driverFactory)
        setContent {
            MyApplicationTheme {
                Scaffold(
                    topBar = { TopAppBar(title = { Text("Worship songs") }) },
                    bottomBar = { BottomBar() }
                ) {
                    SongList()
                }
            }
        }
    }
}

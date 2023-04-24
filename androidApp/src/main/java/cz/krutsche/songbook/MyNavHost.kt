package cz.krutsche.songbook

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import cz.krutsche.songbook.screens.Favorites
import cz.krutsche.songbook.screens.Home
import cz.krutsche.songbook.screens.Settings
import cz.krutsche.songbook.screens.Song

@Composable
fun MyNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") { Home(navController) }
        composable("Favorites") { Favorites(navController) }
        composable(
            "Song/{songNumber}",
            arguments = listOf(navArgument("songNumber") { type = NavType.LongType })
        ) {
            Song(it.arguments?.getLong("songNumber"))
        }
        composable("Settings") { Settings() }
    }
}
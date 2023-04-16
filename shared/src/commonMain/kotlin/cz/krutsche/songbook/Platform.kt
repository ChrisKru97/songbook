package cz.krutsche.songbook

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
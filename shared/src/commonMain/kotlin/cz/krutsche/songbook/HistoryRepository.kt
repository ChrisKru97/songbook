package cz.krutsche.songbook

import cz.krutsche.songbook.sqldelight.Song
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    fun listSongs(): Flow<List<Song>>
    fun addToHistory(songNumber: Number)
}

class HistoryRepositoryImpl(private val db: Database) : HistoryRepository {
    override fun listSongs(): Flow<List<Song>> {
        TODO("Not yet implemented")
    }

    override fun addToHistory(songNumber: Number) {
        TODO("Not yet implemented")
    }
}
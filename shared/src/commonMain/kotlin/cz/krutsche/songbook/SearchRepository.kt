package cz.krutsche.songbook

import cz.krutsche.songbook.sqldelight.Song
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun listSearchedSongs(): Flow<List<Song>>
    fun searchSong(search: String)
}

class SearchRepositoryImpl(private val db: Database) : SearchRepository {
    override fun listSearchedSongs(): Flow<List<Song>> {
        TODO("Not yet implemented")
    }

    override fun searchSong(search: String) {
        TODO("Not yet implemented")
    }
}
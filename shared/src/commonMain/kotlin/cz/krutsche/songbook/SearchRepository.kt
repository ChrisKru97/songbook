package cz.krutsche.songbook

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import cz.krutsche.songbook.sqldelight.Song
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow

interface SearchRepository {
    fun listSearchedSongs(): Flow<Flow<List<Song>>>
    suspend fun searchSong(search: String)
}

class SearchRepositoryImpl(private val db: Database) : SearchRepository {
    private val searchChannel = Channel<String>()

    override fun listSearchedSongs(): Flow<Flow<List<Song>>> =
        searchChannel.receiveAsFlow().map { db.songQueries.search(it).asFlow().mapToList() }

    override suspend fun searchSong(search: String) =
        searchChannel.send("%${deburr(search).lowercase()}%")
}
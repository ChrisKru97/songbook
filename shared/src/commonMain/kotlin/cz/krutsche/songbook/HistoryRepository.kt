package cz.krutsche.songbook

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import cz.krutsche.songbook.sqldelight.List as DbList
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock

interface HistoryRepository {
    fun listSongs(): Flow<List<DbList>>
    fun addToHistory(songNumber: Int)
}

class HistoryRepositoryImpl(private val db: Database) : HistoryRepository {
    override fun listSongs(): Flow<List<DbList>> =
        db.historyQueries.list().asFlow().mapToList()

    override fun addToHistory(songNumber: Int) =
        db.historyQueries.add(songNumber, Clock.System.now().epochSeconds)
}
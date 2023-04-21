package cz.krutsche.songbook

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.orderBy
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import cz.krutsche.songbook.sqldelight.Song as DbSong

interface SongRepository {
    fun searchSongs(text: String): List<Song>
    fun listSongs(): Flow<List<DbSong>>
    suspend fun updateSongs()
    fun setFavorite(number: Long, nextValue: Boolean)
    suspend fun initialize()
    fun getSong(number: Long): DbSong?
}

@OptIn(DelicateCoroutinesApi::class)
class SongRepositoryImpl(private val db: Database) : SongRepository {
    private val loginDeferred: Deferred<Any> = GlobalScope.async {
        Firebase.auth.signInAnonymously()
    }

    override suspend fun initialize() {
        val query = db.songQueries.songCount().execute()
        val next = query.next()
        if (!next) return
        val count = query.getLong(0)?.toInt()
        if (count == null || count == 0) {
            loginDeferred.await()
            fetchSongs()
        }
    }

    private suspend fun fetchSongs() {
        val collection = Firebase.firestore.collection("songs")
        val songs = collection
            .where("checkRequired", false)
            .orderBy("number")
            .get()
            .documents
        songs.forEach {
            db.songQueries.add(songToDb(it.data(Song.serializer())))
        }
    }

    override suspend fun updateSongs() {
        fetchSongs()
    }

    override fun setFavorite(number: Long, nextValue: Boolean) =
        db.songQueries.updateFavorite(number = number, favorite = if (nextValue) 1 else 0)

    override fun searchSongs(text: String): List<Song> {
        return listOf()
    }

    override fun listSongs(): Flow<List<DbSong>> =
        db.songQueries.listAll().asFlow().mapToList()

    override fun getSong(number: Long) =
        db.songQueries.getSong(number).executeAsOneOrNull()
}
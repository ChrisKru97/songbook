package cz.krutsche.songbook

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
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
import cz.krutsche.songbook.sqldelight.Song

interface SongRepository {
    var songCount: Int?
    suspend fun fetchSongs()
    fun searchSongs(text: String): List<Song>
    fun listSongs(): Flow<List<Song>>
    fun setFavorite(number: Int, nextValue: Boolean)
    suspend fun initialize()
    fun getSong(number: Int): Flow<Song?>
    fun listFavorites(): Flow<List<Song>>
}

@OptIn(DelicateCoroutinesApi::class)
class SongRepositoryImpl(private val db: Database) : SongRepository {
    override var songCount: Int? = null

    private val loginDeferred: Deferred<Any> = GlobalScope.async {
        Firebase.auth.signInAnonymously()
    }

    override suspend fun initialize() {
        val query = db.songQueries.songCount().execute()
        val next = query.next()
        if (!next) return
        songCount = query.getLong(0)?.toInt()
        if (songCount == null || songCount == 0) {
            loginDeferred.await()
            fetchSongs()
        }
    }

    override suspend fun fetchSongs() {
        val collection = Firebase.firestore.collection("songs")
        val songs = collection
            .where("checkRequired", false)
            .orderBy("number")
            .get()
            .documents
        db.songQueries.transaction {
            songs.forEach {
                db.songQueries.add(songToDb(it.data(SerializableSong.serializer())))
            }
        }
    }

    override fun setFavorite(number: Int, nextValue: Boolean) =
        db.songQueries.updateFavorite(number = number, favorite = if (nextValue) 1 else 0)

    override fun searchSongs(text: String): List<Song> {
        return listOf()
    }

    override fun listSongs(): Flow<List<Song>> =
        db.songQueries.listAll().asFlow().mapToList()

    override fun getSong(number: Int) =
        db.songQueries.getSong(number).asFlow().mapToOneOrNull()

    override fun listFavorites(): Flow<List<Song>> =
        db.songQueries.listFavorites().asFlow().mapToList()
}
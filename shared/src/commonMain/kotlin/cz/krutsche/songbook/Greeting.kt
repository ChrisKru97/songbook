package cz.krutsche.songbook

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.orderBy
import dev.gitlive.firebase.firestore.where
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

@OptIn(DelicateCoroutinesApi::class)
class Greeting {
    private val loginDeferred: Deferred<Any> = GlobalScope.async {
        Firebase.auth.signInAnonymously()
    }

    suspend fun getSongs(): List<Song> {
        loginDeferred.await()
        val collection = Firebase.firestore.collection("songs")
        val songs = collection
            .where("checkRequired", false)
            .orderBy("number")
            .get()
            .documents
        return songs.map {
            it.data(Song.serializer())
        }
    }
}
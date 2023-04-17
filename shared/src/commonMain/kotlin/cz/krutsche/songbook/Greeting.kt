package cz.krutsche.songbook

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.firestore.firestore
import dev.gitlive.firebase.firestore.orderBy
import dev.gitlive.firebase.firestore.where

class Greeting {
    private val platform: Platform = getPlatform()

    suspend fun getSongs(): String {
        val id = Firebase.auth.signInAnonymously().user?.uid ?: "no id"
        val collection = Firebase.firestore.collection("songs")
        val songs = collection
            .where("checkRequired", false)
            .orderBy("number").get().documents
        return id
    }

    fun greet(): String {
//        db.collection("cities").document("LA").set(City.serializer(), city, encodeDefaults = true)

        return "Hello, ${platform.name}!"
    }
}
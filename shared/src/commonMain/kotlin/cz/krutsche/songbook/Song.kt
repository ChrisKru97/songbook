package cz.krutsche.songbook

import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val name: String,
    val number: Int,
    val withChords: String,
    val withoutChords: String
)

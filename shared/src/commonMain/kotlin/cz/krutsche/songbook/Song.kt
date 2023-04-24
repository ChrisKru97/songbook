package cz.krutsche.songbook

import cz.krutsche.songbook.sqldelight.Song
import kotlinx.serialization.Serializable

@Serializable
data class SerializableSong(
    val name: String,
    val number: Int,
    val withChords: String?,
    val withoutChords: String,
    val favorite: Boolean = false
)

fun songToDb(song: SerializableSong) = Song(
    name = song.name,
    number = song.number,
    withChords = song.withChords,
    withoutChords = song.withoutChords,
    favorite = null
)
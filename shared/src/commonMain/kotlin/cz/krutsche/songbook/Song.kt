package cz.krutsche.songbook

import cz.krutsche.songbook.sqldelight.Song as DbSong
import kotlinx.serialization.Serializable

@Serializable
data class Song(
    val name: String,
    val number: Long,
    val withChords: String?,
    val withoutChords: String,
    val favorite: Boolean = false
)

fun songToDb(song: Song) = DbSong(
    name = song.name,
    number = song.number,
    withChords = song.withChords,
    withoutChords = song.withoutChords,
    favorite = null
)
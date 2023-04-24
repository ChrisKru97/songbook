package cz.krutsche.songbook

import cz.krutsche.songbook.sqldelight.Song
import doist.x.normalize.Form
import doist.x.normalize.normalize
import kotlinx.serialization.Serializable

@Serializable
data class SerializableSong(
    val name: String,
    val number: Int,
    val withChords: String?,
    val withoutChords: String,
    val favorite: Boolean = false
)

val REGEX_UNACCENT = "\\p{InCombiningDiacriticalMarks}+".toRegex()

fun deburr(value: String): String {
    val temp = value.normalize(Form.NFD)
    return REGEX_UNACCENT.replace(temp, "")
}

fun songToDb(song: SerializableSong) = Song(
    name = song.name,
    cleanName = deburr(song.name),
    number = song.number,
    withChords = song.withChords,
    withoutChords = song.withoutChords,
    cleanText = deburr(song.withoutChords),
    favorite = null
)
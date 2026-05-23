package uk.co.dancingganesh.pocketvedas

/**
 * Data models mirroring the iOS Models.swift
 */

data class Book(
    val id: Long,
    val name: String,
    val coverData: ByteArray?
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Book) return false
        return id == other.id
    }

    override fun hashCode(): Int = id.hashCode()
}

data class Bookmark(
    val id: Long,
    val path: String,
    val name: String,
    val description: String,
    val smart: Boolean,
    val offset: Double
)

data class SearchResult(
    val id: Long,
    val title: String,
    val snippet: String,
    val path: String
)

data class ReaderPage(
    val id: Long,
    val title: String,
    val path: String,
    val html: String
)

data class SiblingResult(
    val paths: List<String>,
    val currentIndex: Int
)

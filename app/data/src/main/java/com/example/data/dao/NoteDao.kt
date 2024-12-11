package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.model.NoteEntity
import com.example.data.model.NoteWithCategory
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("UPDATE notes SET is_favourite = :isFavourite WHERE id = :noteId")
    suspend fun updateFavouriteStatus(noteId: Int, isFavourite: Boolean)

    @Transaction
    @Query("SELECT * FROM notes")
    fun getNotesWithCategories(): Flow<List<NoteWithCategory>>

    @Insert
    suspend fun insertNewNote(note: NoteEntity)

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :word || '%'")
    suspend fun findNoteWithWord(word: String): List<NoteWithCategory>

    @Query("UPDATE notes SET timestamp = :newTimestamp WHERE id = :noteId")
    suspend fun updateLastOpenTimestamp(noteId: Int, newTimestamp: Long)

    @Query("UPDATE notes SET share = :share WHERE id = :noteId")
    suspend fun updateShareStatus(noteId: Int, share: Boolean)
}
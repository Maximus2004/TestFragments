package com.example.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.data.model.NoteEntity
import com.example.data.model.NoteWithCategory

@Dao
interface NoteDao {
    @Query("UPDATE notes SET is_favourite = :isFavourite WHERE id = :noteId")
    suspend fun updateFavouriteStatus(noteId: Int, isFavourite: Boolean)

    @Transaction
    @Query("SELECT * FROM notes")
    suspend fun getNotesWithCategories(): List<NoteWithCategory>

    @Insert
    suspend fun insertNewNote(note: NoteEntity)

    @Query("SELECT * FROM notes WHERE title LIKE '%' || :word || '%'")
    suspend fun findNoteWithWord(word: String): List<NoteWithCategory>
}
package com.example.domain.api

import com.example.data.model.CategoryEntity
import com.example.data.model.NoteEntity
import com.example.data.model.NoteWithCategory

interface NotesDatabaseRepository {
    suspend fun getAllNotes(): List<NoteWithCategory>
    suspend fun insertNewNote(note: NoteEntity)
    suspend fun findNoteWithWord(word: String): List<NoteWithCategory>
    suspend fun updateFavouriteStatus(noteId: Int, isFavourite: Boolean)
    suspend fun insertNewCategory(category: CategoryEntity)
    suspend fun getAllCategories(): List<CategoryEntity>
}

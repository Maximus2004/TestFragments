package com.example.domain.api

import com.example.data.dao.CategoryDao
import com.example.data.dao.NoteDao
import com.example.data.model.CategoryEntity
import com.example.data.model.NoteEntity
import com.example.data.model.NoteWithCategory
import kotlinx.coroutines.flow.Flow

class NotesDatabaseRepositoryImpl(
    private val noteDao: NoteDao,
    private val categoryDao: CategoryDao
) : NotesDatabaseRepository {
    override fun getAllNotes(): Flow<List<NoteWithCategory>> {
        return noteDao.getNotesWithCategories()
    }

    override suspend fun insertNewNote(note: NoteEntity) {
        noteDao.insertNewNote(note)
    }

    override suspend fun findNoteWithWord(word: String): List<NoteWithCategory> {
        return noteDao.findNoteWithWord(word)
    }

    override suspend fun updateFavouriteStatus(noteId: Int, isFavourite: Boolean) {
        noteDao.updateFavouriteStatus(noteId, isFavourite)
    }

    override suspend fun insertNewCategory(category: CategoryEntity) {
        categoryDao.insertCategory(category)
    }

    override suspend fun getAllCategories(): List<CategoryEntity> {
        return categoryDao.getAllCategories()
    }

    override suspend fun updateLastOpenTimestamp(noteId: Int) {
        noteDao.updateLastOpenTimestamp(noteId, System.currentTimeMillis())
    }

    override suspend fun updateShareStatus(noteId: Int, share: Boolean) {
        noteDao.updateShareStatus(noteId, share)
    }
}
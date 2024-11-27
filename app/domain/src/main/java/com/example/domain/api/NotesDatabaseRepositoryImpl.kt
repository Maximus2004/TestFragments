package com.example.domain.api

import com.example.data.dao.NoteDao
import com.example.data.model.NoteEntity

class NotesDatabaseRepositoryImpl(private val noteDao: NoteDao) : NotesDatabaseRepository {
    override suspend fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    override suspend fun insertNewNote(note: NoteEntity) {
        noteDao.insertNewNote(note)
    }

    override suspend fun findNoteWithWord(word: String): List<NoteEntity> {
        return noteDao.findNoteWithWord(word)
    }
}
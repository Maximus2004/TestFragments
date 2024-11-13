package com.example.domain.api

import com.example.data.dao.NoteDao
import com.example.data.model.NoteEntity

class NotesDatabaseRepositoryImpl(private val noteDao: NoteDao) : NotesDatabaseRepository {
    override fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }

    override fun insertNewNote(note: NoteEntity) {
        noteDao.insertNewNote(note)
    }
}
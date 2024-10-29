package com.example.domain.api

import androidx.room.RoomDatabase
import com.example.data.dao.NoteDao
import com.example.data.model.NoteEntity
import com.example.data.source.NotesDatabase

class NotesDatabaseRepositoryImpl(private val notesDatabase: NotesDatabase): NotesDatabaseRepository {
    private val noteDao: NoteDao = notesDatabase.noteDao()
    override fun getAllNotes(): List<NoteEntity> {
        return noteDao.getAllNotes()
    }
}
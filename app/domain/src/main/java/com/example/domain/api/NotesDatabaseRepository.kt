package com.example.domain.api

import com.example.data.model.NoteEntity

interface NotesDatabaseRepository {
    fun getAllNotes(): List<NoteEntity>
    fun insertNewNote(note: NoteEntity)
}
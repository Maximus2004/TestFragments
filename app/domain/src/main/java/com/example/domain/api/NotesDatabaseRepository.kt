package com.example.domain.api

import com.example.data.model.NoteEntity

interface NotesDatabaseRepository {
    suspend fun getAllNotes(): List<NoteEntity>
    suspend fun insertNewNote(note: NoteEntity)
    suspend fun findNoteWithWord(word: String): List<NoteEntity>
}

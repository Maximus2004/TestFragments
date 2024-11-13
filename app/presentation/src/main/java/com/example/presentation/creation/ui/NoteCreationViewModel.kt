package com.example.presentation.creation.ui

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import com.example.data.model.NoteEntity
import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.notes.models.NoteItem

class NoteCreationViewModel(private val notesDatabaseRepository: NotesDatabaseRepository) :
    ViewModel() {
    fun insertNote(note: NoteItem) {
        val noteEntity = NoteEntity(
            categoryText = note.categoryText,
            noteText = note.noteText,
            isFavourite = note.isFavourite,
            title = note.title,
            categoryColor = note.categoryColor.toArgb()
        )
        notesDatabaseRepository.insertNewNote(noteEntity)
    }
}
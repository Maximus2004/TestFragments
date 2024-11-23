package com.example.presentation.creation.ui

import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.NoteEntity
import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.notes.models.NoteItem
import kotlinx.coroutines.launch

class NoteCreationViewModel(private val notesDatabaseRepository: NotesDatabaseRepository) :
    ViewModel() {
    fun insertNote(note: NoteItem) = viewModelScope.launch {
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
package com.example.presentation.notes.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.notes.models.NoteItem

class NotesScreenViewModel(private val notesDatabaseRepository: NotesDatabaseRepository) :
    ViewModel() {
    fun getAllNotes(): List<NoteItem> {
        return notesDatabaseRepository.getAllNotes().map {
            NoteItem(
                categoryColor = Color(it.categoryColor),
                title = it.title,
                noteText = it.noteText,
                categoryText = it.categoryText,
                isFavourite = it.isFavourite
            )
        }
    }
}
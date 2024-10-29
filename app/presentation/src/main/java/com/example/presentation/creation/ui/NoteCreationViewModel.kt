package com.example.presentation.creation.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.notes.models.NoteItem

class NoteCreationViewModel(private val notesDatabaseRepository: NotesDatabaseRepository): ViewModel() {
    fun getAllNotes(): List<NoteItem> {
        return notesDatabaseRepository.getAllNotes().map {
            NoteItem(
                categoryText = it.categoryText,
                isFavourite = it.isFavourite,
                title = it.title,
                categoryColor = Color(it.categoryColor)
            )
        }
    }
}
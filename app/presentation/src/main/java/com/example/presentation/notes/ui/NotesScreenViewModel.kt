package com.example.presentation.notes.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.notes.models.NoteItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class NotesScreenViewModel(private val notesDatabaseRepository: NotesDatabaseRepository) :
    ViewModel() {

    fun getNoteItems(): Flow<List<NoteItem>> = flow {
        emit(
            notesDatabaseRepository.getAllNotes().map { noteWithCategory ->
                val category = noteWithCategory.category
                val note = noteWithCategory.note
                NoteItem(
                    id = note.id ?: 0,
                    categoryColor = Color((category?.color ?: 0xFFFF0000) as Int),
                    title = note.title,
                    noteText = note.noteText,
                    categoryText = category?.name ?: "Unknown",
                    isFavourite = note.isFavourite
                )
            }
        )
    }

    fun updateFavouriteStatus(noteId: Int, isFavourite: Boolean) = viewModelScope.launch {
        notesDatabaseRepository.updateFavouriteStatus(noteId, isFavourite)
    }
}
package com.example.presentation.notes.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.notes.models.NoteItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

class NotesScreenViewModel(private val notesDatabaseRepository: NotesDatabaseRepository) :
    ViewModel() {
    private val _noteItems = MutableStateFlow<List<NoteItem>>(emptyList())
    val noteItems: StateFlow<List<NoteItem>> = _noteItems

    fun getNoteItems(): Flow<List<NoteItem>> = flow {
        emit(
            notesDatabaseRepository.getAllNotes().map {
                NoteItem(
                    categoryColor = Color(it.categoryColor),
                    title = it.title,
                    noteText = it.noteText,
                    categoryText = it.categoryText,
                    isFavourite = it.isFavourite
                )
            }
        )
    }
}
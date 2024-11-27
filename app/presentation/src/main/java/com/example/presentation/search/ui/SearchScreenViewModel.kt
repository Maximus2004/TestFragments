package com.example.presentation.search.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.notes.models.NoteItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchScreenViewModel(
    private val notesDatabaseRepository: NotesDatabaseRepository
) : ViewModel() {
    private val _noteList = MutableStateFlow<List<NoteItem>>(emptyList())
    val noteList = _noteList.asStateFlow()

    fun findNoteWithWord(search: String) = viewModelScope.launch {
        _noteList.update {
            notesDatabaseRepository.findNoteWithWord(search).map {
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
}
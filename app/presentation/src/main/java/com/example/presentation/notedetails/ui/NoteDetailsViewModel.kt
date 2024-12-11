package com.example.presentation.notedetails.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.api.NotesDatabaseRepository
import kotlinx.coroutines.launch

class NoteDetailsViewModel(private val repository: NotesDatabaseRepository) : ViewModel() {
    fun updateLastOpenTimestamp(noteId: Int) = viewModelScope.launch {
        repository.updateLastOpenTimestamp(noteId)
    }
}
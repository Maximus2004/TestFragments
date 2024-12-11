package com.example.presentation.creation.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.CategoryEntity
import com.example.data.model.NoteEntity
import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.notes.models.NoteItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class NoteCreationViewModel(private val notesDatabaseRepository: NotesDatabaseRepository) :
    ViewModel() {
    private var _categories = MutableStateFlow<List<CategoryEntity>>(emptyList())
    val categories = _categories.asStateFlow()

    fun insertNote(note: NoteItem, selectedCategoryId: Int) = viewModelScope.launch {
        val noteEntity = NoteEntity(
            categoryId = selectedCategoryId,
            noteText = note.noteText,
            isFavourite = note.isFavourite,
            title = note.title,
            timestamp = System.currentTimeMillis(),
            share = false
        )
        notesDatabaseRepository.insertNewNote(noteEntity)
    }

    fun insertNewCategory(category: CategoryEntity) = viewModelScope.launch {
        notesDatabaseRepository.insertNewCategory(category)
    }

    fun getAllCategories() = viewModelScope.launch {
        _categories.update { notesDatabaseRepository.getAllCategories() }
    }
}
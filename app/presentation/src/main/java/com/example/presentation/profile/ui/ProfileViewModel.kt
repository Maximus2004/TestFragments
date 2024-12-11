package com.example.presentation.profile.ui

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.ProfileEntity
import com.example.domain.api.NotesDatabaseRepository
import com.example.domain.api.ProfilesDatabaseRepositoryImpl
import com.example.presentation.notes.models.NoteItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class ProfileViewModel(
    private val profilesDatabaseRepositoryImpl: ProfilesDatabaseRepositoryImpl,
    private val notesDatabaseRepository: NotesDatabaseRepository
) : ViewModel() {

    val sharedNotes: StateFlow<List<NoteItem>> = notesDatabaseRepository.getAllNotes()
        .map { listOfNotes ->
            listOfNotes
                .filter { it.note.share }
                .map { noteWithCategory ->
                    val category = noteWithCategory.category
                    val note = noteWithCategory.note
                    NoteItem(
                        id = note.id ?: 0,
                        categoryColor = Color((category?.color ?: 0xFFFF0000) as Int),
                        title = note.title,
                        noteText = note.noteText,
                        categoryText = category?.name ?: "Unknown",
                        isFavourite = note.isFavourite,
                        timestamp = note.timestamp
                    )
                }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    fun insertProfile(profile: ProfileEntity) = viewModelScope.launch {
        profilesDatabaseRepositoryImpl.insertProfile(profile)
    }

    fun checkIfExist(email: String, password: String) = flow {
        emit(profilesDatabaseRepositoryImpl.checkIfUserExist(email, password))
    }
}
package com.example.presentation.notes.ui

import android.content.Context
import android.content.Intent
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.notes.models.NoteItem
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NotesScreenViewModel(
    private val notesDatabaseRepository: NotesDatabaseRepository
) : ViewModel() {

    val notes: StateFlow<List<NoteItem>> = notesDatabaseRepository.getAllNotes()
        .map {
            it.map { noteWithCategory ->
                val category = noteWithCategory.category
                val note = noteWithCategory.note
                NoteItem(
                    id = note.id ?: 0,
                    categoryColor = Color((category?.color ?: 0xFFFF0000) as Int),
                    title = note.title,
                    noteText = note.noteText,
                    categoryText = category?.name ?: "Unknown",
                    isFavourite = note.isFavourite,
                    timestamp = note.timestamp,

                )
            }
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.Lazily,
            initialValue = emptyList()
        )

    fun updateFavouriteStatus(noteId: Int, isFavourite: Boolean) = viewModelScope.launch {
        notesDatabaseRepository.updateFavouriteStatus(noteId, isFavourite)
    }

    fun shareText(context: Context, text: String, id: Int) = viewModelScope.launch {
        notesDatabaseRepository.updateShareStatus(id, true)
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        val shareIntent = Intent.createChooser(sendIntent, null)
        context.startActivity(shareIntent)
    }
}
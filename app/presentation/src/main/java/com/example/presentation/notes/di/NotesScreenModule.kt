package com.example.presentation.notes.di

import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.notes.ui.NotesScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val notesScreenModule = module {
    viewModel { NotesScreenViewModel(get<NotesDatabaseRepository>()) }
}
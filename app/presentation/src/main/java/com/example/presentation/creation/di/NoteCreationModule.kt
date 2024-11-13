package com.example.presentation.creation.di

import com.example.domain.api.NotesDatabaseRepository
import com.example.presentation.creation.ui.NoteCreationFragment
import com.example.presentation.creation.ui.NoteCreationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val noteCreationModule = module {
    viewModel { NoteCreationViewModel(get<NotesDatabaseRepository>()) }
    factory { NoteCreationFragment() }
}
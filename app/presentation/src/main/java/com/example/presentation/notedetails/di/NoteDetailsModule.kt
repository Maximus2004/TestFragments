package com.example.presentation.notedetails.di

import com.example.presentation.notedetails.ui.NoteDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val noteDetailsModule = module {
    viewModelOf(::NoteDetailsViewModel)
}
package com.example.presentation.notes.di

import com.example.presentation.notes.ui.NotesFragment
import org.koin.dsl.module

val notesModule = module {
    factory { NotesFragment() }
}
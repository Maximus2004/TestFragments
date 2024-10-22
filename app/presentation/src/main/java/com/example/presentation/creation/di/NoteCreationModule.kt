package com.example.presentation.creation.di

import com.example.presentation.creation.ui.NoteCreationFragment
import org.koin.dsl.module

val noteCreationModule = module {
    factory { NoteCreationFragment() }
}
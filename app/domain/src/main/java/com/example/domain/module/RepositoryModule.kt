package com.example.domain.module

import com.example.data.source.NotesDatabase
import com.example.domain.api.NotesDatabaseRepository
import com.example.domain.api.NotesDatabaseRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NotesDatabaseRepository> {
        NotesDatabaseRepositoryImpl(
            get<NotesDatabase>().noteDao(),
            get<NotesDatabase>().categoryDao()
        )
    }
}
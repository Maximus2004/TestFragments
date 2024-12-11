package com.example.domain.module

import com.example.data.source.NotesDatabase
import com.example.domain.api.NotesDatabaseRepository
import com.example.domain.api.NotesDatabaseRepositoryImpl
import com.example.domain.api.ProfilesDatabaseRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<NotesDatabaseRepository> {
        NotesDatabaseRepositoryImpl(
            get<NotesDatabase>().noteDao(),
            get<NotesDatabase>().categoryDao()
        )
    }
    single {
        ProfilesDatabaseRepositoryImpl(
            get<NotesDatabase>().profileDao(),
        )
    }
}
package com.example.data.module

import com.example.data.source.NotesDatabase
import com.example.data.source.getNotesDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val notesDatabaseModule = module {
    single<NotesDatabase> { getNotesDatabase(androidContext()) }
}
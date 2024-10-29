package com.example.data.source

import org.koin.dsl.module

val notesDatabaseModule = module {
    single { getNotesDatabase(get()) }
}
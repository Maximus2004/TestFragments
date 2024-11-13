package com.example.testfragments

import android.app.Application
import com.example.data.module.notesDatabaseModule
import com.example.domain.module.repositoryModule
import com.example.presentation.converter.di.converterModule
import com.example.presentation.creation.di.noteCreationModule
import com.example.presentation.main.di.mainScreenModule
import com.example.presentation.message.di.messageModule
import com.example.presentation.notes.di.notesScreenModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class Application: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@Application)
            modules(
                notesDatabaseModule,
                repositoryModule,
                messageModule,
                converterModule,
                mainScreenModule,
                noteCreationModule,
                notesScreenModule
            )
        }
    }
}
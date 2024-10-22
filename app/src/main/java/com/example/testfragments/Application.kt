package com.example.testfragments

import android.app.Application
import com.example.presentation.converter.di.converterModule
import com.example.presentation.message.di.messageModule
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
                messageModule,
                converterModule
            )
        }
    }
}
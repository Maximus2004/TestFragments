package com.example.presentation.main.di

import com.example.presentation.main.ui.MainScreenFragment
import org.koin.dsl.module

val mainScreenModule = module {
    factory { MainScreenFragment() }
}
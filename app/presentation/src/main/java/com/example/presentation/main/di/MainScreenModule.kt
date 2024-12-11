package com.example.presentation.main.di

import com.example.presentation.main.ui.MainScreenFragment
import com.example.presentation.main.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val mainScreenModule = module {
    viewModelOf(::MainViewModel)
    factory { MainScreenFragment() }
}
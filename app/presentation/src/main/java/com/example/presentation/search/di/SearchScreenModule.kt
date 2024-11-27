package com.example.presentation.search.di

import com.example.presentation.search.ui.SearchScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchScreenModule = module {
    viewModel { SearchScreenViewModel(get()) }
}
package com.example.presentation.converter.di

import com.example.presentation.converter.ui.ConverterFragment
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.presentation.converter.ui.ConverterViewModel
import org.koin.core.module.dsl.factoryOf

val converterModule = module {
    viewModelOf(::ConverterViewModel)
    factory { ConverterFragment() }
}
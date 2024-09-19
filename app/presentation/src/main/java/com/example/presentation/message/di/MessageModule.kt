package com.example.presentation.message.di

import com.example.presentation.message.ui.MessageFragment
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module
import com.example.presentation.message.ui.MessageViewModel

val messageModule = module {
    viewModelOf(::MessageViewModel)
    factory { MessageFragment() }
}
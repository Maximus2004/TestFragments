package com.example.domain.actions

sealed interface ConverterActions {
    object Loading: ConverterActions
    data class Success(val result: String): ConverterActions
}
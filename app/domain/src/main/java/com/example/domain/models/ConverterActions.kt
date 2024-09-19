package com.example.domain.models

sealed interface ConverterActions {
    object Loading: ConverterActions
    data class Success(val result: String): ConverterActions
}
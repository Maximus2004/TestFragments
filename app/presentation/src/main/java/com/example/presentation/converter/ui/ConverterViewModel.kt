package com.example.presentation.converter.ui

import androidx.lifecycle.ViewModel
import com.example.domain.models.ConverterActions
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class ConverterViewModel(private val translate: String): ViewModel() {
    private val viewStateFlow: MutableStateFlow<ConverterActions> = MutableStateFlow(ConverterActions.Loading)
    private var viewState: ConverterActions
        get() = viewStateFlow.value
        set(value) { viewStateFlow.update { value } }

    fun observeViewStateFlow(): StateFlow<ConverterActions> = viewStateFlow.asStateFlow()

    fun updateLoadingState() {
        if (translate == "") {
            showLoading(true)
        } else {
            showLoading(false)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        viewState = if (isLoading) ConverterActions.Loading else ConverterActions.Success(translate)
    }
}
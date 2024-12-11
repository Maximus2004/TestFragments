package com.example.presentation.main.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel : ViewModel() {
    private val _authStatus = MutableStateFlow(false)
    val authStatus = _authStatus.asStateFlow()

    fun updateAuthStatus(newStatus: Boolean) {
        _authStatus.update { newStatus }
    }
}
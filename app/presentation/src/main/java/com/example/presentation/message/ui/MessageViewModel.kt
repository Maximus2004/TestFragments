package com.example.presentation.message.ui

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.example.domain.actions.MessageActions

class MessageViewModel: ViewModel() {
    private val viewStateFlow: MutableStateFlow<MessageViewState> = MutableStateFlow(
        MessageViewState()
    )
    private var viewState: MessageViewState
        get() = viewStateFlow.value
        set(value) { viewStateFlow.update { value } }

    fun observeViewStateFlow(): StateFlow<MessageViewState> = viewStateFlow.asStateFlow()

    fun onActionPerformed(messageAction: MessageActions) {
        when (messageAction) {
            is MessageActions.onLoading -> {
                showLoading(true)
            }
            is MessageActions.onSuccess -> {
                showLoading(false)
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        viewState = viewState.copy(isLoading = isLoading)
    }
}

data class MessageViewState(val isLoading: Boolean = false)
package com.example.domain.actions

sealed interface MessageActions {
    object onLoading: MessageActions
    object onSuccess: MessageActions
}
package com.example.domain.models

sealed interface MessageActions {
    object onLoading: MessageActions
    object onSuccess: MessageActions
}
package com.example.presentation.notes.models

import androidx.compose.ui.graphics.Color

data class NoteItem(
    val categoryText: String = "Категория",
    val categoryColor: Color,
    val title: String,
    val isFavourite: Boolean
)

val mockNoteItems = listOf(
    NoteItem(
        categoryColor = Color(0xFFFFCD82),
        title = "Название длинное",
        isFavourite = false
    ),
    NoteItem(
        categoryColor = Color(0xFFE16F60),
        title = "Название короткое",
        isFavourite = true
    ),
    NoteItem(
        categoryColor = Color(0xFF87AFEB),
        title = "Название три",
        isFavourite = true
    ),
    NoteItem(
        categoryColor = Color(0xFFB9EB87),
        title = "Название четыре",
        isFavourite = false
    )
)
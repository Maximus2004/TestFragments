package com.example.presentation.notes.models

import androidx.compose.ui.graphics.Color

data class NoteItem(
    val number: Int,
    val categoryText: String = "Категория",
    val categoryColor: Color,
    val title: String,
    val isFavourite: Boolean
)

val mockNoteItems = listOf(
    NoteItem(
        number = 1,
        categoryColor = Color(0xFFFFCD82),
        title = "Название длинное",
        isFavourite = false
    ),
    NoteItem(
        number = 2,
        categoryColor = Color(0xFFE16F60),
        title = "Название короткое",
        isFavourite = true
    ),
    NoteItem(
        number = 3,
        categoryColor = Color(0xFF87AFEB),
        title = "Название три",
        isFavourite = true
    ),
    NoteItem(
        number = 4,
        categoryColor = Color(0xFFB9EB87),
        title = "Название четыре",
        isFavourite = false
    )
)
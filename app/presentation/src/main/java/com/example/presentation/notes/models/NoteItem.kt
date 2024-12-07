package com.example.presentation.notes.models

import androidx.compose.ui.graphics.Color

data class NoteItem(
    val id: Int? = null,
    val categoryText: String = "Категория",
    val categoryColor: Color,
    val title: String,
    val noteText: String,
    val isFavourite: Boolean
)

val mockNoteItems = listOf(
    NoteItem(
        id = 1,
        categoryColor = Color(0xFFFFCD82),
        title = "Название длинное",
        isFavourite = false,
        noteText = "Содержание заметки"
    ),
    NoteItem(
        id = 2,
        categoryColor = Color(0xFFE16F60),
        title = "Название короткое",
        isFavourite = true,
        noteText = "Оооооочень длинное описание заметки"
    ),
    NoteItem(
        id = 3,
        categoryColor = Color(0xFF87AFEB),
        title = "Название три",
        isFavourite = true,
        noteText = "Среднее описание"
    ),
    NoteItem(
        id = 4,
        categoryColor = Color(0xFFB9EB87),
        title = "Название четыре",
        isFavourite = false,
        noteText = "Ну соусоу"
    )
)
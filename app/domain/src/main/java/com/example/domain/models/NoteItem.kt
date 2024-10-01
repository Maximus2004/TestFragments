package com.example.domain.models

import android.graphics.Color

data class NoteItem(
    val number: Int,
    val category: Int,
    val title: String,
    val isFavourite: Boolean
)

val mockNoteItems = listOf(
    NoteItem(
        number = 1,
        category = Color.BLUE,
        title = "Название 1",
        isFavourite = false
    ),
    NoteItem(
        number = 2,
        category = Color.CYAN,
        title = "Название 2",
        isFavourite = false
    ),
    NoteItem(
        number = 3,
        category = Color.GREEN,
        title = "Название 3",
        isFavourite = false
    ),
    NoteItem(
        number = 4,
        category = Color.RED,
        title = "Название 4",
        isFavourite = false
    )
)
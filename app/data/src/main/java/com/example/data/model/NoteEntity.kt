package com.example.data.model

import android.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NoteEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo("category_text") val categoryText: String = "Категория",
    @ColumnInfo("category_color") val categoryColor: Int,
    val title: String,
    @ColumnInfo("is_favourite") val isFavourite: Boolean
)
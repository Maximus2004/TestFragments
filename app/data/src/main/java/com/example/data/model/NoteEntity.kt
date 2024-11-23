package com.example.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo("category_text") val categoryText: String = "Категория",
    @ColumnInfo("category_color") val categoryColor: Int,
    val title: String,
    @ColumnInfo("note_text") val noteText: String,
    @ColumnInfo("is_favourite") val isFavourite: Boolean
)
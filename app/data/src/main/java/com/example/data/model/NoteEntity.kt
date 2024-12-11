package com.example.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["category_id"])]
)
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    @ColumnInfo("category_id") val categoryId: Int,
    val title: String,
    val timestamp: Long,
    val share: Boolean,
    @ColumnInfo("note_text") val noteText: String,
    @ColumnInfo("is_favourite") val isFavourite: Boolean
)
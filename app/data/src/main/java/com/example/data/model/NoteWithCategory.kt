package com.example.data.model

import androidx.room.Embedded
import androidx.room.Relation

data class NoteWithCategory(
    @Embedded val note: NoteEntity,

    // задаем отношение один-ко-многим и обозначаем,
    // что category_id из note ссылается на id из category
    @Relation(
        parentColumn = "category_id",
        entityColumn = "id",
    )
    val category: CategoryEntity?
)

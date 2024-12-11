package com.example.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.CategoryDao
import com.example.data.dao.NoteDao
import com.example.data.dao.ProfileDao
import com.example.data.model.CategoryEntity
import com.example.data.model.NoteEntity
import com.example.data.model.ProfileEntity

@Database(entities = [NoteEntity::class, CategoryEntity::class, ProfileEntity::class], version = 12)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
    abstract fun profileDao(): ProfileDao
}

fun getNotesDatabase(ctx: Context): NotesDatabase {
    val dbFile = ctx.getDatabasePath("notes.db")
    return Room
        .databaseBuilder(
            context = ctx,
            name = dbFile.absolutePath,
            klass = NotesDatabase::class.java
        )
        .fallbackToDestructiveMigration()
        .build()
}
package com.example.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.NoteDao
import com.example.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}

fun getNotesDatabase(ctx: Context): NotesDatabase {
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("notes.db")
    return Room.databaseBuilder(
        context = appContext,
        name = dbFile.absolutePath,
        klass = NotesDatabase::class.java
    ).build()
}
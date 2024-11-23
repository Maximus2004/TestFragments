package com.example.data.source

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.dao.NoteDao
import com.example.data.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 3)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}

fun getNotesDatabase(ctx: Context): NotesDatabase {
    val dbFile = ctx.getDatabasePath("notes.db")
    return Room.databaseBuilder(
        context = ctx,
        name = dbFile.absolutePath,
        klass = NotesDatabase::class.java
    ).build()
}
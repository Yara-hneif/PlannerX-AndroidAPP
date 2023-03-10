package com.example.plannerX.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.plannerX.data.entities.Note
import com.example.plannerX.data.entities.Todo
import com.example.plannerX.data.note.NoteDao
import com.example.plannerX.data.todo.TodoDao

@Database(
    entities = [Note::class, Todo::class],
    version = 3,
    exportSchema = false
)
@TypeConverters(DatabaseConverter::class)
abstract class AppNotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun toDoDao(): TodoDao
}
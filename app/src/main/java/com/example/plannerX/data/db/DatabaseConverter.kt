package com.example.plannerX.data.db

import androidx.room.TypeConverter
import com.example.plannerX.data.entities.Priority

class DatabaseConverter {

    @TypeConverter
    fun fromPriority(priority: Priority): String {
        return priority.name
    }

    @TypeConverter
    fun toPriority(priority: String): Priority {
        return Priority.valueOf(priority)
    }

}
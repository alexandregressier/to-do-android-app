package dev.gressier.todo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.gressier.todo.data.models.Task

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false,
)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}
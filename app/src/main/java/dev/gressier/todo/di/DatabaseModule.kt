package dev.gressier.todo.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.gressier.todo.data.TaskDao
import dev.gressier.todo.data.ToDoDatabase
import dev.gressier.todo.util.Constants
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ToDoDatabase =
        Room.databaseBuilder(context, ToDoDatabase::class.java, Constants.DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideTaskDao(db: ToDoDatabase): TaskDao =
        db.taskDao()
}
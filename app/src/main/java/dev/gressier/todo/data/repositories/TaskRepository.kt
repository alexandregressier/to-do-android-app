package dev.gressier.todo.data.repositories

import dagger.hilt.android.scopes.ViewModelScoped
import dev.gressier.todo.data.TaskDao
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.data.models.TaskId
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ViewModelScoped
class TaskRepository @Inject constructor(
    private val taskDao: TaskDao,
) {

    fun getTask(id: TaskId): Flow<Task?> =
        taskDao.getTask(id)

    val getAllTasks: Flow<List<Task>> =
        taskDao.getAllTasks()

    fun getTasksByPriority(priority: Task.Priority) =
        taskDao.getTasksByPriority(priority)

    val getTasksByLowestPriority: Flow<List<Task>> =
        taskDao.getTasksByLowestPriority()

    val getTasksByHighestPriority: Flow<List<Task>> =
        taskDao.getTasksByHighestPriority()

    fun searchTasks(text: String): Flow<List<Task>> =
        taskDao.searchTasks(text)

    suspend fun addTask(task: Task): TaskId =
        taskDao.addTask(task)

    suspend fun updateTask(task: Task) {
        taskDao.updateTask(task)
    }

    suspend fun deleteTask(task: Task) {
        taskDao.deleteTask(task)
    }

    suspend fun deleteAllTasks() {
        taskDao.deleteAllTasks()
    }
}
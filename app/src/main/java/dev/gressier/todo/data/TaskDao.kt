package dev.gressier.todo.data

import androidx.room.*
import androidx.room.OnConflictStrategy.IGNORE
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.data.models.TaskId
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Query("SELECT * FROM task WHERE id = :id")
    fun getTask(id: TaskId): Flow<Task?>

    @Query("SELECT * FROM task ORDER BY id ASC")
    fun getAllTasks(): Flow<List<Task>>

    @Query("""
        SELECT * FROM task
        ORDER BY CASE
            WHEN priority = 'LOW' THEN 1
            WHEN priority = 'MEDIUM' THEN 2
            WHEN priority = 'HIGH' THEN 3
        END
    """)
    fun getTasksByLowestPriority(): Flow<List<Task>>

    @Query("""
        SELECT * FROM task
        ORDER BY CASE
            WHEN priority = 'HIGH' THEN 1
            WHEN priority = 'MEDIUM' THEN 2
            WHEN priority = 'LOW' THEN 3
        END
    """)
    fun getTasksByHighestPriority(): Flow<List<Task>>

    @Query("SELECT * FROM task WHERE title LIKE :text OR description LIKE :text")
    fun searchTasks(text: String): Flow<List<Task>>

    @Insert(onConflict = IGNORE)
    suspend fun addTask(task: Task): TaskId

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task")
    suspend fun deleteAllTasks()
}
package dev.gressier.todo.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.data.models.TaskId
import dev.gressier.todo.data.repositories.TaskRepository
import dev.gressier.todo.navigation.TaskListAction
import dev.gressier.todo.util.Config
import dev.gressier.todo.util.RequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val taskRepository: TaskRepository,
) : ViewModel() {

    private val _tasks = MutableStateFlow<RequestState<List<Task>>>(RequestState.Idle)
    val tasks: StateFlow<RequestState<List<Task>>> = _tasks

    val searchTasksTopBarState = mutableStateOf(SearchTasksTopBarState.CLOSED)
    val searchText = mutableStateOf("")

    val taskId: MutableState<TaskId?> = mutableStateOf(null)
    val title = mutableStateOf("")
    val description = mutableStateOf("")
    val priority = mutableStateOf(Task.Priority.NONE)

    fun getAllTasks() {
        _tasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                taskRepository.getAllTasks.collect { tasks ->
                    _tasks.value = RequestState.Success(tasks)
                }
            }
        } catch (e: Exception) {
            Log.e("SharedViewModel", "Could not load all the tasks")
            _tasks.value = RequestState.Error(e)
        }
    }

    fun loadTaskInTaskForm(id: TaskId) {
        try {
            viewModelScope.launch {
                taskRepository.getTask(id).collect { task ->
                    task?.also {
                        taskId.value = it.id
                        title.value = it.title
                        description.value = it.description
                        priority.value = it.priority
                    }
                }
            }
        } catch (e: Exception) {
            Log.e("SharedViewModel", "Could not load Task with id `$id`")
        }
    }

    fun updateTaskTitle(title: String) {
        if (title.length <= Config.MAX_TASK_TITLE_LENGTH)
            this.title.value = title
    }

    fun isTaskFormValid(): Boolean =
        listOf(title, description)
            .map(MutableState<String>::value)
            .all(String::isNotEmpty)

    fun resetTaskForm() {
        taskId.value = null
        title.value = ""
        description.value = ""
        priority.value = Task.Priority.NONE
    }

    fun handleTaskListAction(action: TaskListAction) {
        when (action) {
            TaskListAction.ADD -> addTaskFromTaskForm()
            TaskListAction.UPDATE -> updateTaskFromTaskForm()
            TaskListAction.DELETE -> deleteTaskFromTaskForm()
            TaskListAction.DELETE_ALL -> TODO()
            TaskListAction.UNDO -> TODO()
            TaskListAction.NO_ACTION ->  {}
        }
    }

    private fun addTaskFromTaskForm() {
        viewModelScope.launch(Dispatchers.IO) {
            taskRepository.addTask(
                Task(title = title.value, description = description.value, priority = priority.value)
            )
        }
    }

    private fun updateTaskFromTaskForm() {
        taskId.value?.let { taskId ->
            viewModelScope.launch(Dispatchers.IO) {
                taskRepository.updateTask(Task(taskId, title.value, description.value, priority.value))
            }
        }
    }

    private fun deleteTaskFromTaskForm() {
        taskId.value?.let { taskId ->
            viewModelScope.launch(Dispatchers.IO) {
                taskRepository.deleteTask(Task(taskId, title.value, description.value, priority.value))
            }
        }
    }
}

enum class SearchTasksTopBarState { OPENED, CLOSED, TRIGGERED }
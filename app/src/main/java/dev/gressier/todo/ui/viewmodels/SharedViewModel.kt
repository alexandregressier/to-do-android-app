package dev.gressier.todo.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.data.models.TaskId
import dev.gressier.todo.data.repositories.TaskRepository
import dev.gressier.todo.util.RequestState
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

    fun resetTaskForm() {
        title.value = ""
        description.value = ""
        priority.value = Task.Priority.NONE
    }
}

enum class SearchTasksTopBarState { OPENED, CLOSED, TRIGGERED }
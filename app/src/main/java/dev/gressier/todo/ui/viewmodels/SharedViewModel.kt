package dev.gressier.todo.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.gressier.todo.data.models.Task
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

    fun getAllTasks() {
        _tasks.value = RequestState.Loading
        try {
            viewModelScope.launch {
                taskRepository.getAllTasks.collect { tasks ->
                    _tasks.value = RequestState.Success(tasks)
                }
            }
        } catch (e: Exception) {
            _tasks.value = RequestState.Error(e)
        }

    }
}

enum class SearchTasksTopBarState { OPENED, CLOSED, TRIGGERED }
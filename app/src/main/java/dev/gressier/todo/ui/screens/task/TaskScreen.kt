package dev.gressier.todo.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.data.models.TaskId
import dev.gressier.todo.navigation.NavigateToTaskListScreen
import dev.gressier.todo.ui.viewmodels.SharedViewModel

@Composable
fun TaskScreen(
    sharedViewModel: SharedViewModel,
    taskId: TaskId? = null,
    navigateToTaskListScreen: NavigateToTaskListScreen = {},
) {
    LaunchedEffect(Unit) {
        taskId?.let { sharedViewModel.loadTaskInTaskForm(it) }
            ?: sharedViewModel.resetTaskForm()
    }
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Task.Priority by sharedViewModel.priority

    Scaffold(
        topBar = { TaskTopBar(forEdit = taskId != null, navigateToTaskListScreen) },
        content = {
            TaskForm(
                title, { sharedViewModel.title.value = it },
                priority, { sharedViewModel.priority.value = it },
                description, { sharedViewModel.description.value = it }
            )
        },
    )
}

//@Preview
//@Composable
//private fun TaskScreen_Preview() {
//    TaskScreen()
//}
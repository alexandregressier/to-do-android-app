package dev.gressier.todo.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import dev.gressier.todo.R
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.data.models.TaskId
import dev.gressier.todo.navigation.NavigateToTaskListScreen
import dev.gressier.todo.navigation.TaskListAction
import dev.gressier.todo.ui.viewmodels.SharedViewModel
import dev.gressier.todo.util.toast

@Composable
fun TaskScreen(
    sharedViewModel: SharedViewModel,
    taskId: TaskId? = null,
    navigateToTaskListScreen: NavigateToTaskListScreen = {},
) {
    LaunchedEffect(Unit) {
        taskId?.let { sharedViewModel.loadTaskInTaskForm(it) }
            ?: sharedViewModel.resetTaskForm()

        sharedViewModel.closeTaskSearch() // TODO: move this closeTaskSearch() call elsewhere
    }
    val title: String by sharedViewModel.title
    val description: String by sharedViewModel.description
    val priority: Task.Priority by sharedViewModel.priority

    val context = LocalContext.current

    Scaffold(
        topBar = { TaskTopBar(forEdit = taskId != null) { action ->
            when (action) {
                TaskListAction.NO_ACTION -> navigateToTaskListScreen(action)
                else ->
                    if (sharedViewModel.isTaskFormValid())
                        navigateToTaskListScreen(action)
                    else
                        context.toast(R.string.toast_task_form_invalid)
            }
        }},
        content = {
            TaskForm(
                title, sharedViewModel::updateTaskTitle,
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
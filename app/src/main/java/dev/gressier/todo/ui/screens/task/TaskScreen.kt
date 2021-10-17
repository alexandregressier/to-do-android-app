package dev.gressier.todo.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.data.models.TaskId
import dev.gressier.todo.navigation.NavigateToTaskListScreen
import dev.gressier.todo.ui.viewmodels.SharedViewModel
import dev.gressier.todo.util.RequestState

@Composable
fun TaskScreen(
    sharedViewModel: SharedViewModel,
    taskId: TaskId? = null,
    navigateToTaskListScreen: NavigateToTaskListScreen = {},
) {
    taskId?.let {
        LaunchedEffect(true) {
            sharedViewModel.getTask(it)
        }
    }
    val task: RequestState<Task> by sharedViewModel.task.collectAsState()

    Scaffold(
        topBar = { TaskTopBar(forEdit = task is RequestState.Success, navigateToTaskListScreen) },
        content = { Text("$task") },
    )
}

//@Preview
//@Composable
//private fun TaskScreen_Preview() {
//    TaskScreen()
//}
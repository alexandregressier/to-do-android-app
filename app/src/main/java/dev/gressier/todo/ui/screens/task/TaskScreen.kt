package dev.gressier.todo.ui.screens.task

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import dev.gressier.todo.data.models.TaskId
import dev.gressier.todo.navigation.NavigateToTaskListScreen
import dev.gressier.todo.ui.viewmodels.SharedViewModel

@Composable
fun TaskScreen(
    sharedViewModel: SharedViewModel,
    taskId: TaskId? = null,
    navigateToTaskListScreen: NavigateToTaskListScreen = {},
) {
    Scaffold(
        topBar = { TaskTopBar(navigateToTaskListScreen)},
        content = {},
    )
}

//@Preview
//@Composable
//private fun TaskScreen_Preview() {
//    TaskScreen()
//}
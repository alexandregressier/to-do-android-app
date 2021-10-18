package dev.gressier.todo.ui.screens.tasklist

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import dev.gressier.todo.R
import dev.gressier.todo.navigation.NavigateToTaskScreen
import dev.gressier.todo.navigation.TaskListAction
import dev.gressier.todo.ui.theme.fabBackgroundColor
import dev.gressier.todo.ui.viewmodels.SearchTasksTopBarState
import dev.gressier.todo.ui.viewmodels.SharedViewModel
import kotlinx.coroutines.launch

@Composable
fun TaskListScreen(
    sharedViewModel: SharedViewModel,
    action: TaskListAction,
    navigateToTaskScreen: NavigateToTaskScreen = {},
) {
    LaunchedEffect(Unit) {
        sharedViewModel.getAllTasks()
    }
    val scope = rememberCoroutineScope()
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(action) {
        sharedViewModel.handleTaskListAction(action)
        if (action != TaskListAction.NO_ACTION) {
            scope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    "${action.name}: ${sharedViewModel.title.value}",
                    "DISMISS".takeUnless { action == TaskListAction.DELETE } ?: "UNDO",
                ).also {
                    if (it == SnackbarResult.ActionPerformed && action == TaskListAction.DELETE) {
                        sharedViewModel.restoreLastDeletedTask()
                    }
                }
            }
        }
    }

    val tasks by sharedViewModel.tasks.collectAsState()
    val searchTasksTopBarState: SearchTasksTopBarState by sharedViewModel.searchTasksTopBarState
    val searchText: String by sharedViewModel.searchText

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TaskListTopBar(sharedViewModel, searchTasksTopBarState, searchText) },
        content = { TaskListContent(tasks, navigateToTaskScreen) },
        floatingActionButton = { AddTaskFab { navigateToTaskScreen(null) } },
    )
}

@Composable
fun AddTaskFab(onClick: () -> Unit) {
    FloatingActionButton(onClick, backgroundColor = MaterialTheme.colors.fabBackgroundColor) {
        Icon(
            Icons.Filled.Add,
            stringResource(R.string.description_add_task),
            tint = Color.White,
        )
    }
}

//@Preview
//@Composable
//private fun TaskListScreen_Preview() {
//    TaskListScreen()
//}
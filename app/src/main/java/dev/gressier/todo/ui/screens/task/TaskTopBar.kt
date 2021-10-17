package dev.gressier.todo.ui.screens.task

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.R
import dev.gressier.todo.navigation.NavigateToTaskListScreen
import dev.gressier.todo.navigation.TaskListAction.ADD
import dev.gressier.todo.navigation.TaskListAction.NO_ACTION
import dev.gressier.todo.ui.theme.topBarBackgroundColor
import dev.gressier.todo.ui.theme.topBarContentColor

@Composable
fun TaskTopBar(
    navigateToTaskListScreen: NavigateToTaskListScreen = {},
) {
    AddTaskTopBar(navigateToTaskListScreen)
}

@Composable
fun AddTaskTopBar(
    navigateToTaskListScreen: NavigateToTaskListScreen = {},
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.title_add_task), color = MaterialTheme.colors.topBarContentColor)
        },
        navigationIcon = { GoBackAction(navigateToTaskListScreen) },
        actions = { AddTaskAction(navigateToTaskListScreen) },
        backgroundColor = MaterialTheme.colors.topBarBackgroundColor,
    )
}

@Composable
fun GoBackAction(onClick: NavigateToTaskListScreen) {
    IconButton({ onClick(NO_ACTION) }) {
        Icon(
            Icons.Filled.ArrowBack,
            stringResource(R.string.icon_back_arrow),
            tint = MaterialTheme.colors.topBarContentColor,
        )
    }
}

@Composable
fun AddTaskAction(onClick: NavigateToTaskListScreen) {
    IconButton({ onClick(ADD) }) {
        Icon(
            Icons.Filled.Check,
            stringResource(R.string.icon_check),
            tint = MaterialTheme.colors.topBarContentColor,
        )
    }
}

@Composable
fun EditTaskTopBar() {
//    TopAppBar()
}

@Preview
@Composable
private fun AddTaskTopBar_Preview() {
    AddTaskTopBar()
}

@Preview
@Composable
private fun EditTaskTopBar_Preview() {
    EditTaskTopBar()
}
package dev.gressier.todo.ui.screens

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.R
import dev.gressier.todo.data.models.TaskId

@Composable
fun TaskListScreen(navigateToTask: (TaskId) -> Unit) {
    Scaffold(
        content = {},
        floatingActionButton = { AddTaskFab { navigateToTask(-1) } },
    )
}

@Composable
fun AddTaskFab(onClick: () -> Unit) {
    FloatingActionButton(onClick) {
        Icon(Icons.Filled.Add, stringResource(R.string.description_add_task), tint = Color.White)
    }
}

@Preview
@Composable
fun TaskListScreenPreview() {
    TaskListScreen(navigateToTask = {})
}
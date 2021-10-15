package dev.gressier.todo.ui.screens.tasklist

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.ui.theme.topBarBackgroundColor
import dev.gressier.todo.ui.theme.topBarContentColor

@Composable
fun TaskListTopBar() {
    DefaultTaskListTopBar()
}

@Composable
fun DefaultTaskListTopBar() {
    TopAppBar(
        title = { Text("Tasks", color = MaterialTheme.colors.topBarContentColor) },
        backgroundColor = MaterialTheme.colors.topBarBackgroundColor,
    )
}

@Preview
@Composable
private fun DefaultTaskListTopBarPreview() {
    DefaultTaskListTopBar()
}
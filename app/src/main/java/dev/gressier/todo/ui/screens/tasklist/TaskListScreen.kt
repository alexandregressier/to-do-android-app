package dev.gressier.todo.ui.screens.tasklist

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import dev.gressier.todo.R
import dev.gressier.todo.ui.theme.fabBackgroundColor
import dev.gressier.todo.ui.viewmodels.SearchTasksTopBarState
import dev.gressier.todo.ui.viewmodels.SharedViewModel

@Composable
fun TaskListScreen(
    sharedViewModel: SharedViewModel,
    onAddTaskFabClick: () -> Unit = {},
) {
    val searchTasksTopBarState: SearchTasksTopBarState by sharedViewModel.searchTasksTopBarState
    val searchText: String by sharedViewModel.searchText

    Scaffold(
        topBar = { TaskListTopBar(sharedViewModel, searchTasksTopBarState, searchText) },
        content = {},
        floatingActionButton = { AddTaskFab(onAddTaskFabClick) },
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
//private fun TaskListScreenPreview() {
//    TaskListScreen()
//}
package dev.gressier.todo.ui.screens.tasklist

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.R
import dev.gressier.todo.components.TaskPriorityItem
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.ui.theme.LargePadding
import dev.gressier.todo.ui.theme.Typography
import dev.gressier.todo.ui.theme.topBarBackgroundColor
import dev.gressier.todo.ui.theme.topBarContentColor

@Composable
fun TaskListTopBar() {
    DefaultTaskListTopBar()
}

@Composable
fun DefaultTaskListTopBar(
    onSearchTasksClick: () -> Unit = {},
    onSortTasksClick: (Task.Priority) -> Unit = {},
    onShowMoreClick: () -> Unit = {},
) {
    TopAppBar(
        title = { Text("Tasks", color = MaterialTheme.colors.topBarContentColor) },
        actions = {
            SearchTasksAction(onSearchTasksClick)
            SortTasksAction(onSortTasksClick)
            ShowMoreAction(onShowMoreClick)
        },
        backgroundColor = MaterialTheme.colors.topBarBackgroundColor,
    )
}

@Composable
fun SearchTasksAction(onClick: () -> Unit) {
    IconButton(onClick) {
        Icon(
            Icons.Filled.Search,
            stringResource(R.string.description_search_tasks),
            tint = MaterialTheme.colors.topBarContentColor,
        )
    }
}

@Composable
fun SortTasksAction(onClick: (Task.Priority) -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painterResource(R.drawable.ic_filter_list),
            stringResource(R.string.description_sort_tasks),
            tint = MaterialTheme.colors.topBarContentColor,
        )
        DropdownMenu(expanded, onDismissRequest = { expanded = false }) {
            Task.Priority.values().forEach { taskPriority ->
                DropdownMenuItem({ onClick(taskPriority); expanded = false }) {
                    TaskPriorityItem(taskPriority)
                }
            }
        }
    }
}

@Composable
fun ShowMoreAction(onClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painterResource(R.drawable.ic_more_vert),
            stringResource(R.string.description_show_more),
            tint = MaterialTheme.colors.topBarContentColor,
        )
        DropdownMenu(expanded, onDismissRequest = { expanded = false }) {
            // Delete all tasks
            DropdownMenuItem({ onClick(); expanded = false }) {
                Text(
                    stringResource(R.string.description_delete_all_tasks),
                    Modifier.padding(start = LargePadding),
                    style = Typography.subtitle2,
                )
            }
        }
    }
}

@Preview
@Composable
private fun DefaultTaskListTopBarPreview() {
    DefaultTaskListTopBar()
}
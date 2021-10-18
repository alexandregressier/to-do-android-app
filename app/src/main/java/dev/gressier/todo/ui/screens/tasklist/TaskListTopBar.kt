package dev.gressier.todo.ui.screens.tasklist

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.R
import dev.gressier.todo.components.TaskPriorityItem
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.ui.theme.*
import dev.gressier.todo.ui.viewmodels.SharedViewModel

@Composable
fun TaskListTopBar(
    sharedViewModel: SharedViewModel,
    isSearchBarOpened: Boolean,
    searchText: String,
) {
    if (!isSearchBarOpened)
        DefaultTaskListTopBar(
            onSearchTasksClick = sharedViewModel::openTaskSearch,
            onSortTasksClick = {},
            onDeleteAllClick = sharedViewModel::deleteAllTasks,
        )
    else
        SearchTasksAppBar(
            text = searchText,
            onTextChange = {
                sharedViewModel.searchText.value = it
                sharedViewModel.searchTasks()
            },
            onSearchClick = {},
            onCloseClick = sharedViewModel::closeTaskSearch,
        )
}

@Composable
fun DefaultTaskListTopBar(
    onSearchTasksClick: () -> Unit = {},
    onSortTasksClick: (Task.Priority) -> Unit = {},
    onDeleteAllClick: () -> Unit = {},
) {
    TopAppBar(
        title = {
            Text(stringResource(R.string.title_task_list), color = MaterialTheme.colors.topBarContentColor)
        },
        actions = {
            SearchTasksAction(onSearchTasksClick)
            SortTasksAction(onSortTasksClick)
            ShowMoreAction(onDeleteAllClick)
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
            Task.Priority.values().forEach { priority ->
                DropdownMenuItem({ onClick(priority); expanded = false }) {
                    TaskPriorityItem(priority)
                }
            }
        }
    }
}

@Composable
fun ShowMoreAction(onDeleteAllClick: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            painterResource(R.drawable.ic_more_vert),
            stringResource(R.string.description_show_more),
            tint = MaterialTheme.colors.topBarContentColor,
        )
        DropdownMenu(expanded, onDismissRequest = { expanded = false }) {
            // Delete all tasks
            DropdownMenuItem({ onDeleteAllClick(); expanded = false }) {
                Text(
                    stringResource(R.string.description_delete_all_tasks),
                    Modifier.padding(start = largePadding),
                    style = Typography.subtitle2,
                )
            }
        }
    }
}

@Composable
fun SearchTasksAppBar(
    text: String = "",
    onTextChange: (String) -> Unit = {},
    onSearchClick: (String) -> Unit = {},
    onCloseClick: () -> Unit = {},
) {
    var deleteTextBeforeClosing by remember { mutableStateOf(false) }

    Surface(
        Modifier.fillMaxWidth().height(topBarHeight),
        color = MaterialTheme.colors.topBarBackgroundColor,
        elevation = AppBarDefaults.TopAppBarElevation,
    ) {
        TextField(
            text,
            onTextChange,
            Modifier.fillMaxWidth(),
            textStyle = TextStyle(MaterialTheme.colors.topBarContentColor, MaterialTheme.typography.subtitle1.fontSize),
            placeholder = {
                Text(
                    stringResource(R.string.placeholder_search_tasks),
                    Modifier.alpha(ContentAlpha.medium),
                    color = Color.White,
                )
            },
            leadingIcon = {
                IconButton({}, Modifier.alpha(ContentAlpha.disabled)) {
                    Icon(
                        Icons.Filled.Search,
                        stringResource(R.string.description_search_tasks),
                        tint = MaterialTheme.colors.topBarContentColor,
                    )
                }
            },
            trailingIcon = {
                IconButton({
                    if (deleteTextBeforeClosing) {
                        onTextChange("")
                        deleteTextBeforeClosing = false
                    } else {
                        if (text.isNotEmpty()) {
                            onTextChange("")
                        } else {
                            onCloseClick()
                            deleteTextBeforeClosing = true
                        }
                    }
                }) {
                    Icon(
                        Icons.Filled.Close,
                        stringResource(R.string.description_close_search),
                        tint = MaterialTheme.colors.topBarContentColor,
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearchClick(text) }),
            singleLine = true,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                cursorColor = MaterialTheme.colors.topBarContentColor,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
            ),
        )
    }
}

@Preview
@Composable
private fun DefaultTaskListTopBar_Preview() {
    DefaultTaskListTopBar()
}

@Preview
@Composable
private fun SearchTasksAppBar_Preview() {
    SearchTasksAppBar()
}
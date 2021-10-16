package dev.gressier.todo.ui.screens.tasklist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.data.models.TaskId

@Composable
fun TaskListContent(tasks: List<Task>, navigateToTask: (TaskId) -> Unit = {}) {
    if (tasks.isNotEmpty())
        LazyColumn {
            items(tasks, { it.id }) { task ->
                TaskListItem(task, navigateToTask)
            }
        }
    else
        EmptyTaskListContent()
}

@Preview
@Composable
private fun TaskListContent_Preview() {
    TaskListContent(List(5) { i ->
        Task(
            id = i.toLong(),
            title = "Something to do",
            description = "This is something that has to be done",
            priority = Task.Priority.values().run { get(i % count()) }
        )
    })
}
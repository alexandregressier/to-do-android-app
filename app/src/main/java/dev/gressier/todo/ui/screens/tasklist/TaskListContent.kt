package dev.gressier.todo.ui.screens.tasklist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.data.models.TaskId
import dev.gressier.todo.util.RequestState

@Composable
fun TaskListContent(tasks: RequestState<List<Task>>, navigateToTask: (TaskId) -> Unit = {}) {
    when (tasks) {
        is RequestState.Success ->
           if (tasks.value.isNotEmpty())
               LazyColumn {
                   items(tasks.value, { it.id }) { task ->
                       TaskListItem(task, navigateToTask)
                   }
               }
           else
               EmptyTaskListContent()
    }

}

@Preview
@Composable
private fun TaskListContent_Preview() {
    TaskListContent(
        RequestState.Success(List(5) { i ->
            Task.example.copy(
                id = i.toLong(),
                priority = Task.Priority.values().run { get(i % count()) },
            )
        }
    ))
}
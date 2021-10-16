package dev.gressier.todo.ui.screens.tasklist

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.data.models.TaskId
import dev.gressier.todo.ui.theme.*

@Composable
fun TaskListItem(task: Task, navigateToTask: (TaskId) -> Unit = {}) {
    with (task) {
        Surface(
            Modifier.fillMaxWidth().clickable { navigateToTask(id) },
            RectangleShape,
            MaterialTheme.colors.taskListItemBackgroundColor,
            elevation = taskListItemElevation,
        ) {
            Column(Modifier.padding(largePadding).fillMaxWidth()) {
                Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween, Alignment.CenterVertically) {
                    Text(
                        title,
                        color = MaterialTheme.colors.taskListItemTextColor,
                        fontWeight = FontWeight.Bold,
                        overflow = TextOverflow.Ellipsis,
                        maxLines = 1,
                        style = MaterialTheme.typography.h5,
                    )
                    Canvas(Modifier.size(taskPriorityIndicatorSize)) {
                        drawCircle(priority.color)
                    }
                }
                Text(
                    description,
                    Modifier.fillMaxWidth(),
                    MaterialTheme.colors.taskListItemTextColor,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2,
                    style = MaterialTheme.typography.subtitle1,
                )
            }
        }
    }
}

@Preview
@Composable
private fun TaskListItemPreview() {
    TaskListItem(Task(
        title = "Something to do",
        description = "This is something that has to be done",
        priority = Task.Priority.LOW
    ))
}
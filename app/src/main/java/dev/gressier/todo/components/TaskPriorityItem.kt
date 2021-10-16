package dev.gressier.todo.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.ui.theme.Typography
import dev.gressier.todo.ui.theme.largePadding
import dev.gressier.todo.ui.theme.taskPriorityIndicatorSize
import dev.gressier.todo.util.capitalize

@Composable
fun TaskPriorityItem(taskPriority: Task.Priority) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Canvas(Modifier.size(taskPriorityIndicatorSize)) {
            drawCircle(taskPriority.color)
        }
        Text(
            taskPriority.name.capitalize(),
            Modifier.padding(start = largePadding),
            MaterialTheme.colors.onSurface,
            style = Typography.subtitle1,
        )
    }
}

@Preview
@Composable
private fun TaskPriorityItemPreview() {
    TaskPriorityItem(Task.Priority.HIGH)
}
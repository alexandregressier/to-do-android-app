package dev.gressier.todo.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.ui.theme.Typography
import dev.gressier.todo.ui.theme.largePadding
import dev.gressier.todo.util.capitalize

@Composable
fun TaskPriorityItem(priority: Task.Priority) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        TaskPriorityIndicator(priority)
        Text(
            priority.name.capitalize(),
            Modifier.padding(start = largePadding),
            MaterialTheme.colors.onSurface,
            style = Typography.subtitle1,
        )
    }
}

@Preview
@Composable
private fun TaskPriorityItem_Preview() {
    TaskPriorityItem(Task.Priority.HIGH)
}
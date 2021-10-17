package dev.gressier.todo.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.ui.theme.taskPriorityIndicatorSize

@Composable
fun TaskPriorityIndicator(priority: Task.Priority) {
    Canvas(Modifier.size(taskPriorityIndicatorSize)) {
        drawCircle(priority.color)
    }
}
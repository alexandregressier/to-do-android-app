package dev.gressier.todo.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gressier.todo.R
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.ui.theme.Typography
import dev.gressier.todo.ui.theme.taskPriorityDropdownMenuHeight
import dev.gressier.todo.util.capitalize

@Composable
fun TaskPriorityDropdownMenu(
    priority: Task.Priority,
    onSelect: (Task.Priority) -> Unit = {},
) {
    var expanded by remember { mutableStateOf(false) }
    val angle: Float by animateFloatAsState(if (expanded) 180f else 0f)

    Row(
        Modifier
            .fillMaxWidth()
            .height(taskPriorityDropdownMenuHeight)
            .background(MaterialTheme.colors.background)
            .clickable { expanded = true }
            .border(1.dp, MaterialTheme.colors.onSurface.copy(ContentAlpha.disabled), MaterialTheme.shapes.small),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.weight(1f), Alignment.Center) {
            TaskPriorityIndicator(priority)
        }
        Text(
            priority.name.capitalize(),
            Modifier.weight(8f),
            style = Typography.subtitle1,
        )
        IconButton(
            { expanded = true },
            Modifier
                .alpha(ContentAlpha.medium)
                .rotate(angle)
                .weight(1.5f)
        ) {
            Icon(
                Icons.Filled.ArrowDropDown,
                stringResource(R.string.icon_arrow_dropdown),
            )
        }
        DropdownMenu(expanded, onDismissRequest = { expanded = false }, Modifier.fillMaxWidth(0.94f)) {
            Task.Priority.values().toList().asReversed().forEach { priority ->
                DropdownMenuItem({ onSelect(priority); expanded = false }) {
                    TaskPriorityItem(priority)
                }
            }
        }
    }
}

@Preview
@Composable
private fun TaskPriorityDropdownMenu_Preview() {
    TaskPriorityDropdownMenu(Task.Priority.HIGH)
}
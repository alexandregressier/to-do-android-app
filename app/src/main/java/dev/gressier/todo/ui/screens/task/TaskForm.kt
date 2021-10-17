package dev.gressier.todo.ui.screens.task

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.R
import dev.gressier.todo.components.TaskPriorityDropdownMenu
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.ui.theme.largePadding
import dev.gressier.todo.ui.theme.mediumPadding

@Composable
fun TaskForm(
    title: String = "",
    onTitleChange: (String) -> Unit = {},
    priority: Task.Priority = Task.Priority.NONE,
    onPrioritySelect: (Task.Priority) -> Unit = {},
    description: String = "",
    onDescriptionChange: (String) -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(largePadding)
    ) {
        OutlinedTextField(
            title,
            onTitleChange,
            Modifier.fillMaxWidth(),
            textStyle = MaterialTheme.typography.body1,
            label = { Text(stringResource(R.string.field_label_task_title)) },
            singleLine = true,
        )
        Spacer(Modifier.height(mediumPadding))
        TaskPriorityDropdownMenu(priority, onPrioritySelect)
        OutlinedTextField(
            description,
            onDescriptionChange,
            Modifier.fillMaxSize(),
            textStyle = MaterialTheme.typography.body1,
            label = { Text(stringResource(R.string.field_label_task_description)) },
        )
    }
}

@Preview
@Composable
private fun TaskForm_Preview() {
    TaskForm()
}
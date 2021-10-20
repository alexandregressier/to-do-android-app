package dev.gressier.todo.components

import androidx.compose.material.AlertDialog
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import dev.gressier.todo.R

@Composable
fun Alert(
    isPresented: Boolean,
    title: String,
    message: String,
    onYesClick: () -> Unit,
    onNoClick: () -> Unit,
) {
    if (isPresented)
        AlertDialog(
            onDismissRequest = { onNoClick() },
            title = {
                Text(
                    title,
                    fontSize = MaterialTheme.typography.h5.fontSize,
                    fontWeight = FontWeight.Bold,
                )
            },
            text = {
                Text(
                    message,
                    fontSize = MaterialTheme.typography.subtitle1.fontSize,
                    fontWeight = FontWeight.Normal,
                )
            },
            confirmButton = { TextButton({ onYesClick(); onNoClick() }) {
                Text(stringResource(R.string.alert_yes_button).uppercase()) }
            },
            dismissButton = { TextButton({ onNoClick() }) {
                Text(stringResource(R.string.alert_no_button).uppercase()) }
            },
        )
}
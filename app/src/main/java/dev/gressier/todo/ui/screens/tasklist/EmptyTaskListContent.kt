package dev.gressier.todo.ui.screens.tasklist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.gressier.todo.R
import dev.gressier.todo.ui.theme.MediumGray

@Composable
fun EmptyTaskListContent() {
    Column(
        Modifier.fillMaxSize().background(MaterialTheme.colors.background),
        Arrangement.Center,
        Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(R.drawable.ic_sentiment_satisfied_alt),
            stringResource(R.string.icon_happy_face),
            Modifier.size(120.dp),
            MediumGray,
        )
        Text(
            stringResource(R.string.text_all_tasks_completed),
            color = MediumGray,
            fontSize = MaterialTheme.typography.h6.fontSize,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Preview
@Composable
private fun EmptyTaskListContent_Preview() {
    EmptyTaskListContent()
}
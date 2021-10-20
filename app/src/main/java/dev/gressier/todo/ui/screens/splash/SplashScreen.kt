package dev.gressier.todo.ui.screens.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import dev.gressier.todo.R
import dev.gressier.todo.ui.theme.ToDoTheme
import dev.gressier.todo.ui.theme.splashScreenBackgroundColor
import dev.gressier.todo.ui.theme.splashScreenLogoHeight
import dev.gressier.todo.util.Config
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    navigateToTaskListScreen: () -> Unit = {},
) {
    LaunchedEffect(Unit) {
        delay(Config.SPLASH_SCREEN_DURATION)
        navigateToTaskListScreen()
    }
    Box(Modifier.fillMaxSize().background(MaterialTheme.colors.splashScreenBackgroundColor), Alignment.Center) {
       Image(
           painterResource(R.drawable.ic_logo_light.takeUnless { isSystemInDarkTheme() } ?: R.drawable.ic_logo_dark),
           stringResource(R.string.description_todo_app_logo),
           Modifier.size(splashScreenLogoHeight),
       )
    }
}

@Preview
@Composable
private fun SplashScreen_Preview() {
    SplashScreen()
}

@Preview
@Composable
private fun SplashScreen_Dark_Preview() {
    ToDoTheme(darkTheme = true) {
        SplashScreen()
    }
}
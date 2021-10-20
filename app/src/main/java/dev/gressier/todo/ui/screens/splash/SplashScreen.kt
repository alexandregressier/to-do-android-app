package dev.gressier.todo.ui.screens.splash

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
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
    var startAnimation by remember { mutableStateOf(false) }
    val offsetState by animateDpAsState(
        if (startAnimation) 0.dp else splashScreenLogoHeight,
        tween((Config.SPLASH_SCREEN_DURATION * 0.8).toInt())
    )
    val alphasState by animateFloatAsState(
        if (startAnimation) 1f else 0f,
        tween((Config.SPLASH_SCREEN_DURATION * 0.8).toInt())
    )

    LaunchedEffect(Unit) {
        startAnimation = true
        delay(Config.SPLASH_SCREEN_DURATION)
        navigateToTaskListScreen()
    }
    Box(
        Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.splashScreenBackgroundColor)
            .offset(y = offsetState)
            .alpha(alphasState),
        Alignment.Center,
    ) {
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
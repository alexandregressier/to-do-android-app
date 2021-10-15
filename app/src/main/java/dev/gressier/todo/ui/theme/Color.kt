package dev.gressier.todo.ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val Purple200 = Color(0xFFBB86FC)
val Purple500 = Color(0xFF6200EE)
val Purple700 = Color(0xFF3700B3)
val Teal200 = Color(0xFF03DAC5)

val LightGray = Color(0xFFFCFCFC)
val MediumGray = Color(0xFF9C9C9C)
val DarkGray = Color(0xFF141414)

val NoPriorityColor = Color(0xFFFFFFFF)
val LowPriorityColor = Color(0xFF00C980)
val MediumPriorityColor = Color(0xFFFFC114)
val HighPriorityColor = Color(0xFFFF4646)

// Top bar
val Colors.topBarBackgroundColor: Color
    @Composable get() = Purple500.takeIf { isLight } ?: Color.Black

val Colors.topBarContentColor: Color
    @Composable get() = Color.White.takeIf { isLight } ?: LightGray

// FAB
val Colors.fabBackgroundColor: Color
    @Composable get() = Teal200.takeIf { isLight } ?: Purple700
package dev.gressier.todo.util

import java.util.*

fun String.capitalize(): String =
    lowercase().replaceFirstChar { it.titlecase(Locale.getDefault()) }
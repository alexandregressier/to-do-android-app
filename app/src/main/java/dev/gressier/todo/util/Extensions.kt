package dev.gressier.todo.util

import android.content.Context
import android.widget.Toast
import androidx.annotation.StringRes
import java.util.*

fun String.capitalize(): String =
    lowercase().replaceFirstChar { it.titlecase(Locale.getDefault()) }

fun Context.toast(@StringRes resId: Int) =
    Toast.makeText(this, getString(resId), Toast.LENGTH_SHORT).show()
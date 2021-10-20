package dev.gressier.todo.data.models

import androidx.compose.ui.graphics.Color
import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.gressier.todo.ui.theme.HighPriorityColor
import dev.gressier.todo.ui.theme.LowPriorityColor
import dev.gressier.todo.ui.theme.MediumPriorityColor
import dev.gressier.todo.ui.theme.NoPriorityColor

typealias TaskId = Long

@Entity
data class Task(
    @PrimaryKey(autoGenerate = true) val id: TaskId = 0,
    val title: String,
    val description: String,
    val priority: Priority,
) {
    enum class Priority(val color: Color) {
        HIGH(HighPriorityColor),
        MEDIUM(MediumPriorityColor),
        LOW(LowPriorityColor),
        NONE(NoPriorityColor),
    }

    enum class SortingOrder { DESCENDING, ASCENDING, NONE }

    companion object {
        val example = Task(
            title = "Something to do",
            description = "This is something that has to be done",
            priority = Priority.LOW,
        )
    }
}
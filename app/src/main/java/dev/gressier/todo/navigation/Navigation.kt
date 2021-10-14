package dev.gressier.todo.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.gressier.todo.data.models.TaskId
import dev.gressier.todo.ui.screens.TaskListScreen

class NavigateTo(navController: NavHostController) {

    val taskList: (TaskAction) -> Unit = { action ->
        navController.navigate("taskList/${action.name}") {
            popUpTo("taskList/{action}") { inclusive = true }
        }
    }
    val task: (TaskId) -> Unit = { taskId ->
        navController.navigate("task/${taskId}")
    }
}

@Composable
fun SetupNavigation(navController: NavHostController) {
    val navigateTo: NavigateTo = remember(navController) { NavigateTo(navController) }

    NavHost(navController, startDestination = "taskList/{action}") {
        taskListComposable(navigateToTask = navigateTo.task)
        taskComposable(navigateToTaskList = navigateTo.taskList)
    }
}

fun NavGraphBuilder.taskListComposable(
    navigateToTask: (TaskId) -> Unit,
) {
    composable(
        route = "taskList/{action}",
        arguments = listOf(
            navArgument("action") { type = NavType.StringType },
        )
    ) { TaskListScreen(navigateToTask) }
}

fun NavGraphBuilder.taskComposable(
    navigateToTaskList: (TaskAction) -> Unit,
) {
    composable(
        route = "task/{taskId}",
        arguments = listOf(
            navArgument("taskId") { type = NavType.LongType },
        )
    ) {}
}
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
import dev.gressier.todo.ui.screens.tasklist.TaskListScreen
import dev.gressier.todo.ui.viewmodels.SharedViewModel

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
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
    val navigateTo: NavigateTo = remember(navController) { NavigateTo(navController) }

    NavHost(navController, startDestination = "taskList/{action}") {
        taskListComposable(sharedViewModel, navigateTo.task)
        taskComposable(navigateTo.taskList)
    }
}

fun NavGraphBuilder.taskListComposable(
    sharedViewModel: SharedViewModel,
    navigateToTask: (TaskId) -> Unit,
) {
    composable(
        route = "taskList/{action}",
        arguments = listOf(
            navArgument("action") { type = NavType.StringType },
        )
    ) { TaskListScreen(sharedViewModel) }
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
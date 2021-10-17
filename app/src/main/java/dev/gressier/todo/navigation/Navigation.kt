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
import dev.gressier.todo.ui.screens.task.TaskScreen
import dev.gressier.todo.ui.screens.tasklist.TaskListScreen
import dev.gressier.todo.ui.viewmodels.SharedViewModel

typealias NavigateToTaskListScreen = (TaskListAction) -> Unit
typealias NavigateToTaskScreen = (TaskId?) -> Unit

class NavigateTo(navController: NavHostController) {

    val taskListScreen: NavigateToTaskListScreen = { action ->
        navController.navigate("taskList/${action.name}") {
            popUpTo("taskList/{action}") { inclusive = true }
        }
    }
    val taskScreen: NavigateToTaskScreen = { taskId ->
        navController.navigate("task/${taskId ?: -1}")
    }
}

@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
    val navigateTo: NavigateTo = remember(navController) { NavigateTo(navController) }

    NavHost(navController, startDestination = "taskList/{action}") {
        taskListComposable(sharedViewModel, navigateTo.taskScreen)
        taskComposable(sharedViewModel, navigateTo.taskListScreen)
    }
}

fun NavGraphBuilder.taskListComposable(
    sharedViewModel: SharedViewModel,
    navigateToTask: NavigateToTaskScreen,
) {
    composable(
        route = "taskList/{action}",
        arguments = listOf(
            navArgument("action") { type = NavType.StringType },
        ),
    ) { backStackEntry ->
        TaskListScreen(sharedViewModel, navigateToTask)
    }
}

fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToTaskListScreen: NavigateToTaskListScreen,
) {
    composable(
        route = "task/{taskId}",
        arguments = listOf(
            navArgument("taskId") { type = NavType.LongType },
        ),
    ) { backStackEntry ->
        backStackEntry.arguments?.getLong("taskId")?.let { taskId ->
            TaskScreen(sharedViewModel, taskId)
        }
    }
}
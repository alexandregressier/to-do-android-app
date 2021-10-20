package dev.gressier.todo.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import dev.gressier.todo.data.models.TaskId
import dev.gressier.todo.ui.screens.splash.SplashScreen
import dev.gressier.todo.ui.screens.task.TaskScreen
import dev.gressier.todo.ui.screens.tasklist.TaskListScreen
import dev.gressier.todo.ui.viewmodels.SharedViewModel

typealias NavigateToTaskListScreen = (TaskListAction) -> Unit
typealias NavigateToTaskScreen = (TaskId?) -> Unit

class NavigateTo(navController: NavHostController) {

    val splashScreen: () -> Unit = {
        navController.navigate("taskList/${TaskListAction.NO_ACTION.name}") {
            popUpTo("splash") { inclusive = true }
        }
    }
    val taskListScreen: NavigateToTaskListScreen = { action ->
        navController.navigate("taskList/${action.name}") {
            popUpTo("taskList/{action}") { inclusive = true }
        }
    }
    val taskScreen: NavigateToTaskScreen = { taskId ->
        navController.navigate("task/$taskId")
    }
}

@ExperimentalAnimationApi
@Composable
fun SetupNavigation(
    navController: NavHostController,
    sharedViewModel: SharedViewModel,
) {
    val navigateTo: NavigateTo = remember(navController) { NavigateTo(navController) }

    AnimatedNavHost(navController, startDestination = "splash") {
        splashComposable(navigateTo.splashScreen)
        taskListComposable(sharedViewModel, navigateTo.taskScreen)
        taskComposable(sharedViewModel, navigateTo.taskListScreen)
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.splashComposable(
    navigateToTaskListScreen: () -> Unit,
) {
    composable(route = "splash") {
        SplashScreen(navigateToTaskListScreen)
    }
}

@ExperimentalAnimationApi
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
        val action = backStackEntry.arguments?.getString("action")?.run(TaskListAction::valueOf)
            ?: TaskListAction.NO_ACTION
        TaskListScreen(sharedViewModel, action, navigateToTask)
    }
}

@ExperimentalAnimationApi
fun NavGraphBuilder.taskComposable(
    sharedViewModel: SharedViewModel,
    navigateToTaskListScreen: NavigateToTaskListScreen,
) {
    composable(
        route = "task/{taskId}",
        arguments = listOf(
            navArgument("taskId") { type = NavType.StringType; nullable = true },
        ),
    ) { backStackEntry ->
        val taskId = backStackEntry.arguments?.getString("taskId")?.toLongOrNull()
        TaskScreen(sharedViewModel, taskId, navigateToTaskListScreen)
    }
}
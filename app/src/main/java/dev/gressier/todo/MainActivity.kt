package dev.gressier.todo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.gressier.todo.navigation.SetupNavigation
import dev.gressier.todo.ui.theme.ToDoTheme
import dev.gressier.todo.ui.viewmodels.SharedViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController
    private val sharedViewModel: SharedViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoTheme {
                navController = rememberNavController()
                SetupNavigation(navController, sharedViewModel)
            }
        }
    }
}
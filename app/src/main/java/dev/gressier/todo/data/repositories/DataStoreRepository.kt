package dev.gressier.todo.data.repositories

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dev.gressier.todo.data.models.Task
import dev.gressier.todo.util.Config
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(Config.PREFERENCES_NAME)

@ViewModelScoped
class DataStoreRepository @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    private object Keys {
        val TaskSortingOrder = stringPreferencesKey("Task.SortingOrder")
    }

    val TaskSortingOrder: Flow<Task.SortingOrder> =
        context.dataStore.data
            .catch { e -> when (e) {
                is IOException -> emit(emptyPreferences())
                else -> throw e
            }}
            .map { settings ->
                settings[Keys.TaskSortingOrder]?.let{ Task.SortingOrder.valueOf(it) } ?: Task.SortingOrder.NONE
            }

    suspend fun writeTaskSortingOrder(order: Task.SortingOrder) {
        context.dataStore.edit { settings ->
            settings[Keys.TaskSortingOrder] = order.name
        }
    }
}
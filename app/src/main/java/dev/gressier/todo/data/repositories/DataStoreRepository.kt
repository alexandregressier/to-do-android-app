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
import dev.gressier.todo.data.models.TaskSortingOrder
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
        val taskSortingOrder = stringPreferencesKey("taskSortingOrder")
    }

    val taskSortingOrder: Flow<TaskSortingOrder> =
        context.dataStore.data
            .catch { e -> when (e) {
                is IOException -> emit(emptyPreferences())
                else -> throw e
            }}
            .map { settings ->
                settings[Keys.taskSortingOrder]?.let{ TaskSortingOrder.valueOf(it) } ?: TaskSortingOrder.NONE
            }

    suspend fun writeTaskSortingOrder(order: TaskSortingOrder) {
        context.dataStore.edit { settings ->
            settings[Keys.taskSortingOrder] = order.name
        }
    }
}
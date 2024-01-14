package id.aej.myflix.core.data.source.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by dinopriyano on 11/01/24.
 */
class AppDataStore (
  private val context: Context
) {

  private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "MyFlix.pb")

  suspend fun <T> storeData(key: Preferences.Key<T>, value: T) {
    context.dataStore.edit { preferences ->
      preferences[key] = value
    }
  }

  suspend fun clear() {
    context.dataStore.edit { preferences ->
      preferences.remove(TOKEN)
    }
  }

  val token: Flow<String>
    get() = context.dataStore.data.map { preferences ->
      preferences[TOKEN] ?: ""
    }

  companion object {
    val TOKEN = stringPreferencesKey("TOKEN")
  }
}
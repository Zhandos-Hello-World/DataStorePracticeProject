package kz.strongte.datastorepracticeproject

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kz.strongte.datastorepracticeproject.datastore.UserPreferencesKeys

class MainViewModel(
    private val datastore: DataStore<Preferences>,
) : ViewModel() {
    val name = datastore.data.map { pref ->
        pref[UserPreferencesKeys.USER_NAME] ?: "Unknown"
    }


    fun setUserName(name: String) {
        viewModelScope.launch {
            datastore.edit { pref ->
                pref[UserPreferencesKeys.USER_NAME] = name
            }
        }
    }
}

package kz.strongte.datastorepracticeproject.datastore

import androidx.datastore.preferences.core.stringPreferencesKey

object UserPreferencesKeys {
    val KEY = "USER_PREF_KEY"
    val USER_NAME = stringPreferencesKey("USER_NAME")
}

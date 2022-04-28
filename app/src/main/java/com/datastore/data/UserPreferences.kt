package com.datastore.data

import android.content.Context
import androidx.datastore.DataStore
import androidx.datastore.preferences.Preferences
import androidx.datastore.preferences.createDataStore
import androidx.datastore.preferences.edit
import androidx.datastore.preferences.preferencesKey
import kotlinx.coroutines.flow.first

class UserPreferences(private val context: Context) {

    private val dataStore: DataStore<Preferences> =
        context.createDataStore(name = "user_preferences")

    companion object {
        val KEY_USERNAME = preferencesKey<String>("USER_NAME")
        val PASSWORD = preferencesKey<String>("PASSWORD")
        val TYPE = preferencesKey<Int>("TYPE")
    }

    suspend fun saveUserInformation(username: String, password: String, type: Int) {
        dataStore.edit { preferences ->
            preferences[KEY_USERNAME] = username
            preferences[PASSWORD] = password
            preferences[TYPE] = type
        }
    }

    suspend fun getName(): String? {
        val preferences = dataStore.data.first()
        return preferences[KEY_USERNAME]
    }

    suspend fun getPassword(): String? {
        val preferences = dataStore.data.first()
        return preferences[PASSWORD]
    }

    suspend fun getType(): Int? {
        val preferences = dataStore.data.first()
        return preferences[TYPE]
    }

}
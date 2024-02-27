package com.example.goodfood.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LoginRepository(private val dataStore: DataStore<Preferences>) {
    private val key = booleanPreferencesKey("loggedIn")

    // Menyimpan status login
    suspend fun setLoggedIn(value: Boolean) {
        dataStore.edit { settings ->
            settings[key] = value
        }
    }

    // Mengecek status login
    val isLoggedIn: Flow<Boolean> = dataStore.data.map { preferences ->
        preferences[key] ?: false
    }

}
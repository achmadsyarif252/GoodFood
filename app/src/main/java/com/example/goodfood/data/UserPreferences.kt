package com.example.goodfood.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object UserPreferences {
    private val Context.dataStore by preferencesDataStore("user_preferences")

    private object PreferencesKeys {
        val LOGGED_IN = booleanPreferencesKey("logged_in")
        val USERNAME = stringPreferencesKey("username")
    }

    suspend fun saveUserLogin(context: Context, username: String, isLoggedIn: Boolean) {
        context.dataStore.edit { preferences ->
            preferences[PreferencesKeys.USERNAME] = username
            preferences[PreferencesKeys.LOGGED_IN] = isLoggedIn
        }
    }

    val Context.loginInfo: Flow<LoginInfo>
        get() = dataStore.data.map { preferences ->
            LoginInfo(
                isLoggedIn = preferences[PreferencesKeys.LOGGED_IN] ?: false,
                username = preferences[PreferencesKeys.USERNAME] ?: ""
            )
        }
}

data class LoginInfo(val isLoggedIn: Boolean, val username: String)

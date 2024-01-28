package com.chunmaru.sushishop.data.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    DataStoreManager.PREFERENCES_KEY
)

class DataStoreManager(
    private val context: Context
) {

    private val keyList = listOf(KEY_TO_ADDRESS1, KEY_TO_ADDRESS2, KEY_TO_ADDRESS3)

    suspend fun saveToken(token: String) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey(KEY_TO_TOKEN)] = token
        }
    }

    suspend fun saveAddress(address: String) {
        context.dataStore.edit { preferences ->
            val emptyKey = keyList.firstOrNull { key ->
                preferences[stringPreferencesKey(key)]?.isEmpty() == true
            }

            if (emptyKey != null) {
                preferences[stringPreferencesKey(emptyKey)] = address
            } else {
                keyList.shuffled().firstOrNull()?.let { randomKey ->
                    preferences.remove(stringPreferencesKey(randomKey))
                    preferences[stringPreferencesKey(randomKey)] = address
                } ?: throw IllegalArgumentException("Failed to save address")
            }
        }
    }

    suspend fun deleteAddress(address: String) {
        context.dataStore.edit { preferences ->
            val keyList = listOf(KEY_TO_ADDRESS1, KEY_TO_ADDRESS2, KEY_TO_ADDRESS3)

            val keyWithAddress = keyList.firstOrNull { key ->
                preferences[stringPreferencesKey(key)] == address
            }

            if (keyWithAddress != null)
                preferences.remove(stringPreferencesKey(keyWithAddress))

        }
    }


    private fun getAllAddresses() = context.dataStore.data.map { preferences ->
        listOf(
            preferences[stringPreferencesKey(KEY_TO_ADDRESS1)] ?: "",
            preferences[stringPreferencesKey(KEY_TO_ADDRESS2)] ?: "",
            preferences[stringPreferencesKey(KEY_TO_ADDRESS3)] ?: ""
        ).filter { it.isNotEmpty() }
    }.distinctUntilChanged().flowOn(Dispatchers.IO)


    private fun getToken() =
        context.dataStore.data.map { pref ->
            pref[stringPreferencesKey(KEY_TO_TOKEN)]
        }


    suspend fun saveUserName(name: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(KEY_TO_USER_NAME)] = name
        }
    }

    private fun getUserName() =
        context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(KEY_TO_USER_NAME)] ?: ""
        }.distinctUntilChanged().flowOn(Dispatchers.IO)


    suspend fun savePhoneNumber(phoneNumber: String) {
        context.dataStore.edit { preferences ->
            preferences[stringPreferencesKey(KEY_TO_PHONE)] = phoneNumber
        }
    }

    private fun getPhoneNumber() =
        context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(KEY_TO_PHONE)] ?: ""
        }.distinctUntilChanged().flowOn(Dispatchers.IO)


    suspend fun getLocaleUserData(): LocaleUserData {
        val token = getToken().firstOrNull() ?: ""
        val addresses = getAllAddresses().firstOrNull() ?: emptyList()
        val username = getUserName().firstOrNull() ?: ""
        val phone = getPhoneNumber().firstOrNull() ?: ""

        return LocaleUserData(token, addresses, username, phone)
    }

    suspend fun getAdminToken(): String {
        return context.dataStore.data.map { preferences ->
            preferences[stringPreferencesKey(KEY_TO_ADMIN_TOKEN)] ?: ""
        }.first()
    }


    suspend fun setAdminToken(token: String) {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey(KEY_TO_ADMIN_TOKEN)] = token
        }
    }

    suspend fun removeAdminToken() {
        context.dataStore.edit { pref ->
            pref[stringPreferencesKey(KEY_TO_ADMIN_TOKEN)] = ""
        }
    }


    companion object {
        const val PREFERENCES_KEY = "data_store"
        const val KEY_TO_ADMIN_TOKEN = "admin_token"
        const val KEY_TO_TOKEN = "token"
        const val KEY_TO_ADDRESS1 = "address 1"
        const val KEY_TO_ADDRESS2 = "address 2"
        const val KEY_TO_ADDRESS3 = "address 3"
        const val KEY_TO_USER_NAME = "username"
        const val KEY_TO_PHONE = "user phone"
    }
}

data class LocaleUserData(
    val token: String? = null,
    val addresses: List<String>,
    val username: String,
    val phone: String
)

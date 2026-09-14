package com.example.mobileappclient.Data.Pref

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.mobileappclient.repository.DataStoreOperations
import com.example.mobileappclient.utils.Constants.PREFERENCES_KEY
import com.example.mobileappclient.utils.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


val Context.dataStore : DataStore<androidx.datastore.preferences.core.Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {

    //the key under which we want to save our boolean value
    private object PreferenceKey {
        val onBoarding = booleanPreferencesKey(name = PREFERENCES_KEY)
    }

    //instance of dataStore
    private val dataStore = context.dataStore


    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferenceKey.onBoarding] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data
            .catch { exception ->
                if(exception is IOException){
                    emit(emptyPreferences())
                } else {
                    throw exception
                }
            }
            .map { preferences ->
                val onBoardingState = preferences[PreferenceKey.onBoarding] ?: false
                onBoardingState
            }
    }


}
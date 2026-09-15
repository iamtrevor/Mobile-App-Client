package com.example.mobileappclient.Data.Pref


import com.example.mobileappclient.repository.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val dataStore : DataStoreOperations //hilt will look in the modules for a fun that has DataStoreOperations as the return type
) {

    suspend fun saveOnBoardingState(completed : Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readingOnBoardingState() : Flow<Boolean>{
        return dataStore.readOnBoardingState()
    }

}
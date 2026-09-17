package com.example.mobileappclient.Data.Repository


import androidx.paging.PagingData
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.repository.DataStoreOperations
import com.example.mobileappclient.repository.RemoteDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class Repository @Inject constructor(
    private val remote : RemoteDataSource,
    private val dataStore : DataStoreOperations //hilt will look in the modules for a fun that has DataStoreOperations as the return type
) {

    fun getAllHeroes() : Flow<PagingData<Hero>>{
        return remote.getAllHeroes()
    }


    suspend fun saveOnBoardingState(completed : Boolean) {
        dataStore.saveOnBoardingState(completed = completed)
    }

    fun readingOnBoardingState() : Flow<Boolean>{
        return dataStore.readOnBoardingState()
    }

}
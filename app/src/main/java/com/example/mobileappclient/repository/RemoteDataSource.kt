package com.example.mobileappclient.repository

import androidx.paging.PagingData
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource {

    fun getAllHeroes() : Flow<PagingData<Hero>>
    fun searchHeroes() : Flow<PagingData<Hero>>

}
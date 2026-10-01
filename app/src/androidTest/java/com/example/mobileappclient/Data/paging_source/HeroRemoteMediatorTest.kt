package com.example.mobileappclient.Data.paging_source

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.test.core.app.ApplicationProvider
import com.example.mobileappclient.Data.HeroDatabase
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.Data.RoomDB.Remote.FakeHeroApi2
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class HeroRemoteMediatorTest {

    private lateinit var heroApi : FakeHeroApi2
    private lateinit var heroDatabase: HeroDatabase

    @Before
    fun setup(){
        heroApi = FakeHeroApi2()
        heroDatabase = HeroDatabase.create(
            context = ApplicationProvider.getApplicationContext(),
            useInMemory = true
        )
    }


    @After
    fun cleanup(){
        heroDatabase.clearAllTables()
    }


    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsSuccessResultWheMoreDataIsPresent() =
        runBlocking {
            val remoteMediator = HeroRemoteMediator(
                heroApi = heroApi,
                heroDatabase = heroDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 3
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)

            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertFalse((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }


    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadSuccessAndEndOfPaginationTrueWhenNoMoreData() =
        runBlocking {
            heroApi.clearData()
            val remoteMediator = HeroRemoteMediator(
                heroApi = heroApi,
                heroDatabase = heroDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 3
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)

            assertTrue(result is RemoteMediator.MediatorResult.Success)
            assertTrue((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached)
        }


    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsErrorResultWhenErrorOccurs() =
        runBlocking {
            heroApi.addException()
            val remoteMediator = HeroRemoteMediator(
                heroApi = heroApi,
                heroDatabase = heroDatabase
            )

            val pagingState = PagingState<Int, Hero>(
                pages = listOf(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 3
            )

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)

            assertTrue(result is RemoteMediator.MediatorResult.Error)

        }


}
package com.example.mobileappclient.Data.paging_source


import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.mobileappclient.Data.HeroDatabase
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.Data.RoomBD.Remote.HeroApi
import com.example.mobileappclient.Data.RoomBD.Remote.HeroRemoteKeys
import javax.inject.Inject


@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val heroApi: HeroApi,
    private val heroDatabase: HeroDatabase
): RemoteMediator<Int, Hero>() {


    private val heroDao = heroDatabase.heroDao()
    private val heroRemoteKeyDao = heroDatabase.heroRemoteKeyDao()


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Hero>
    ): MediatorResult {
        return try {

            val page = when(loadType){
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {  //used when scrolling up
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                    prevPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    prevPage
                }
                LoadType.APPEND -> { //used when scrolling down
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                    nextPage ?: return MediatorResult.Success(
                        endOfPaginationReached = remoteKeys != null
                    )
                    nextPage
                }
            }


            val response = heroApi.getAllHeroes(page = page)

            heroDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    heroDao.deleteAllHeroes()
                    heroRemoteKeyDao.deleteAllRemoteKeys()
                }
                if (response.heroes.isNotEmpty()) {
                    val prevPage = response.prevPage
                    val nextPage = response.nextPage

                    val keys = response.heroes.map { hero ->
                        HeroRemoteKeys(
                            id = hero.id,
                            prevPage = prevPage,
                            nextPage = nextPage
                        )
                    }

                    heroRemoteKeyDao.addAllRemoteKey(heroRemoteKeys = keys)
                    heroDao.addHeroes(heroes = response.heroes)
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)

        } catch (e : Exception){
            return MediatorResult.Error(e)
        }
    }


    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state : PagingState<Int, Hero>
    ) : HeroRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeyDao.getRemoteKey(heroId = id)
            }
        }
    }


    private suspend fun getRemoteKeyForFirstItem(
        state : PagingState<Int, Hero>
    ) : HeroRemoteKeys?{

        return state.pages.firstOrNull{ it.data.isNotEmpty() } ?.data?.firstOrNull() ?.let { hero ->
            heroRemoteKeyDao.getRemoteKey(heroId = hero.id)
        }
    }


    private suspend fun getRemoteKeyForLastItem(
        state : PagingState<Int, Hero>
    ) : HeroRemoteKeys? {
        return state.pages.lastOrNull{ it.data.isNotEmpty() }?.data?.lastOrNull()?.let { hero ->
            heroRemoteKeyDao.getRemoteKey(heroId = hero.id)
        }
    }


}



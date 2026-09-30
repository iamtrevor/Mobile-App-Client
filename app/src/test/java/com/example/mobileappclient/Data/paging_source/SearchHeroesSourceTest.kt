package com.example.mobileappclient.Data.paging_source

import androidx.paging.PagingSource.LoadParams
import androidx.paging.PagingSource.LoadResult
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.Data.RoomBD.Remote.FakeHeroApi
import com.example.mobileappclient.Data.RoomBD.Remote.HeroApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class SearchHeroesSourceTest {

    private lateinit var heroApi : HeroApi
    private lateinit var heroes : List<Hero>

    @Before
    fun setUp(){
        heroApi = FakeHeroApi()
        heroes = listOf(
            Hero(
                id = 1,
                name = "Sasuke",
                image = "",
                about = "",
                rating = 5.0,
                power = 0,
                month = "",
                day = "",
                family = listOf(),
                abilities = listOf(),
                natureTypes = listOf()
            ),

            Hero(
                id = 1,
                name = "Naruto",
                image = "",
                about = "",
                rating = 5.0,
                power = 0,
                month = "",
                day = "",
                family = listOf(),
                abilities = listOf(),
                natureTypes = listOf()
            ),

            Hero(
                id = 1,
                name = "Sakura",
                image = "",
                about = "",
                rating = 5.0,
                power = 0,
                month = "",
                day = "",
                family = listOf(),
                abilities = listOf(),
                natureTypes = listOf()
            )

        )
    }

    @Test
    fun `Search api with existing hero name, expect single hero result, assert LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroesSource(heroApi = heroApi, query = "Sasuke")

            assertEquals(
                LoadResult.Page<Int, Hero>(
                    data = listOf(heroes.first()),
                    prevKey = null,
                    nextKey = null
                ),
                heroSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )

        }


    @Test
    fun `Search api with existing hero name, expect multiple hero result, assert LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroesSource(heroApi = heroApi, query = "Sa")

            assertEquals(
                LoadResult.Page<Int, Hero>(
                    data = listOf(heroes.first(), heroes[2]),
                    prevKey = null,
                    nextKey = null
                ),
                heroSource.load(
                    LoadParams.Refresh(
                        key = null,
                        loadSize = 3,
                        placeholdersEnabled = false
                    )
                )
            )
    }


    @Test
    fun `Search api with empty hero name, assert empty heroes list LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroesSource(heroApi = heroApi, query = "Sa")


            val loadResult = heroSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )


            val result = heroApi.searchHeroes("").heroes

            assertTrue ( result.isEmpty() )
            assertTrue ( loadResult is LoadResult.Page )

        }


    @Test
    fun `Search api with non-existing hero name, assert empty heroes list LoadResult_Page`() =
        runTest {
            val heroSource = SearchHeroesSource(heroApi = heroApi, query = "unknown")


            val loadResult = heroSource.load(
                LoadParams.Refresh(
                    key = null,
                    loadSize = 3,
                    placeholdersEnabled = false
                )
            )


            val result = heroApi.searchHeroes("unknown").heroes

            assertTrue ( result.isEmpty() )
            assertTrue ( loadResult is LoadResult.Page )

        }



}
package com.example.mobileappclient.use_cases.search_heroes

import androidx.paging.PagingData
import com.example.mobileappclient.Data.Repository.Repository
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import kotlinx.coroutines.flow.Flow

class SearchHeroesUseCase(
    private val repository : Repository
) {

    operator fun invoke(query : String) : Flow<PagingData<Hero>> {
        return repository.searchHeroes(query = query)
    }

}
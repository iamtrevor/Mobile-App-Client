package com.example.mobileappclient.use_cases.get_all_heroes

import androidx.paging.PagingData
import com.example.mobileappclient.Data.Repository.Repository
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import kotlinx.coroutines.flow.Flow

class GetAllHeroesUseCase(
    private val repository : Repository
) {


    operator fun invoke(): Flow<PagingData<Hero>> {
        return repository.getAllHeroes()
    }

}
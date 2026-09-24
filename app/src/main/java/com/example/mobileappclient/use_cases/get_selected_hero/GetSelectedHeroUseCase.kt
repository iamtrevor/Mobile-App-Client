package com.example.mobileappclient.use_cases.get_selected_hero

import com.example.mobileappclient.Data.Repository.Repository
import com.example.mobileappclient.Data.RoomBD.Local.Hero

class GetSelectedHeroUseCase(
    private val repository: Repository
) {

    suspend operator fun invoke(heroId : Int) : Hero {
        return repository.getSelectedHero(heroId = heroId)
    }

}
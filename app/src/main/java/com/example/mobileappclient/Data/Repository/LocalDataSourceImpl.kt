package com.example.mobileappclient.Data.Repository

import com.example.mobileappclient.Data.HeroDatabase
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.repository.LocalDataSource

class LocalDataSourceImpl(heroDatabase: HeroDatabase) : LocalDataSource {

    val heroDao = heroDatabase.heroDao()

    override suspend fun getSelectedHero(heroId: Int): Hero {
        return heroDao.getSelectedHero(heroId = heroId)
    }
}
package com.example.mobileappclient.repository

import com.example.mobileappclient.Data.RoomBD.Local.Hero

interface LocalDataSource {

    suspend fun getSelectedHero(heroId : Int) : Hero

}
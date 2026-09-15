package com.example.mobileappclient.Data.RoomBD.Remote

import retrofit2.http.Query
import retrofit2.http.GET

interface HeroApi {


    @GET("/boruto/heroes")
    suspend fun getAllHeroes(
        @Query("page") page : Int = 1
    ) : ApiResponse


    @GET("/boruto/heroes/search")
    suspend fun searchHeroes(
        @Query("name") name : String
    ) : ApiResponse


}
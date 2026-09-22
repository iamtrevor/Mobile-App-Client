package com.example.mobileappclient.Data.RoomBD.Remote

import com.example.mobileappclient.Data.RoomBD.Local.Hero
import kotlinx.serialization.Serializable


//will be used to fetch the response from the backend server
//convert back the json
@Serializable
data class ApiResponse(
    val success : Boolean,
    val message : String? = null,
    val prevPage : Int? = null,
    val nextPage : Int? = null,
    val heroes : List<Hero> = emptyList(),
    val lastUpdated : Long? = null
)

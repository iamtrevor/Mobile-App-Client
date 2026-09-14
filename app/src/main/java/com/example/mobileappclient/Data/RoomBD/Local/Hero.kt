package com.example.mobileappclient.Data.RoomBD.Local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.mobileappclient.utils.Constants

@Entity(Constants.HERO_DATABASE_TABLE)
data class Hero(

    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val image : String,
    val about: String,
    val rating : Double,
    val power: Int,
    val month: String, //birth month of the hero
    val day: String, // birth+day of the hero
    val family : List<String>, //a list of string to list all the heroes family members
    val abilities: List<String>,
    val natureTypes : List<String>
)
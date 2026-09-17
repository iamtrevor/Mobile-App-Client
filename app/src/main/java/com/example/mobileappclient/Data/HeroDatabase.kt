package com.example.mobileappclient.Data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.Data.RoomBD.Local.HeroDao
import com.example.mobileappclient.Data.RoomBD.Remote.HeroRemoteKeyDao
import com.example.mobileappclient.Data.RoomBD.Remote.HeroRemoteKeys


@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase : RoomDatabase() {


    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao


}
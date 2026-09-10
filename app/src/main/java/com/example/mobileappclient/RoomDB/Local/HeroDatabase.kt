package com.example.mobileappclient.RoomDB.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.mobileappclient.RoomDB.Remote.HeroRemoteKey
import com.example.mobileappclient.RoomDB.Remote.HeroRemoteKeyDao

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
abstract class HeroDatabase : RoomDatabase() {


    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao


}
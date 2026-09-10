package com.example.mobileappclient.RoomDB.Local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.mobileappclient.RoomDB.DatabaseConverter
import com.example.mobileappclient.RoomDB.Remote.HeroRemoteKey
import com.example.mobileappclient.RoomDB.Remote.HeroRemoteKeyDao

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase : RoomDatabase() {


    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao


}
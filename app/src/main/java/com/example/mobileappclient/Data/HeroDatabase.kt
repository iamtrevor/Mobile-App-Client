package com.example.mobileappclient.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.mobileappclient.Data.RoomBD.Local.Hero
import com.example.mobileappclient.Data.RoomBD.Local.HeroDao
import com.example.mobileappclient.Data.RoomBD.Remote.HeroApi
import com.example.mobileappclient.Data.RoomBD.Remote.HeroRemoteKeyDao
import com.example.mobileappclient.Data.RoomBD.Remote.HeroRemoteKeys


@Database(entities = [Hero::class, HeroRemoteKeys::class], version = 1)
@TypeConverters(DatabaseConverter::class)
abstract class HeroDatabase : RoomDatabase() {


    companion object {
        fun create(context : Context, useInMemory : Boolean) : HeroDatabase{
            val dataBaseBuilder = if (useInMemory){
                Room.inMemoryDatabaseBuilder(context, HeroDatabase::class.java)
            } else {
                Room.databaseBuilder(context, HeroDatabase::class.java, "test_database.db")
            }

            return dataBaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }


    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao


}
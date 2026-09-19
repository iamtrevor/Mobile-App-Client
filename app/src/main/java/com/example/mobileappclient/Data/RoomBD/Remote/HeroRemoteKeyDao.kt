package com.example.mobileappclient.Data.RoomBD.Remote

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface HeroRemoteKeyDao {


    @Query("SELECT * FROM hero_remote_keys_table WHERE id=:heroId")
    suspend fun getRemoteKey(heroId : Int) : HeroRemoteKeys?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKey(heroRemoteKeys: List<HeroRemoteKeys>)

    @Query("DELETE FROM hero_remote_keys_table")
    suspend fun deleteAllRemoteKeys()

}
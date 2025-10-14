package com.quriogamethechance.quriogame.data.local.lastGame

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LastGameDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
     fun insertLastGame(lastGame: LastGameEntity)

    @Query("SELECT * FROM LAST_GAME_TABLE")
    fun getAllLastGames(): List<LastGameEntity>

}
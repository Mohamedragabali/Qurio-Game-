package com.quriogamethechance.quriogame.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LastGameDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertLastGame(lastGame: LastGameEntity)

    @Query("SELECT * FROM LAST_GAME_TABLE ORDER BY date DESC")
    fun getAllLastGames(): List<LastGameEntity>

}
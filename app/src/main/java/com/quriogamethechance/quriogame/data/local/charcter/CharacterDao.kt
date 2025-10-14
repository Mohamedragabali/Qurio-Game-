package com.quriogamethechance.quriogame.data.local.charcter

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
     fun insertCharacter(character: CharacterEntity)

    @Query("SELECT * FROM CHARACTER_TABLE")
    fun getAllCharacter(): List<CharacterEntity>

}
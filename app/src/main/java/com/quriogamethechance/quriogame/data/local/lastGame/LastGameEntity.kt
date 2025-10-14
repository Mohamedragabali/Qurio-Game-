package com.quriogamethechance.quriogame.data.local.lastGame

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "LAST_GAME_TABLE")
data class LastGameEntity(
    @PrimaryKey(autoGenerate = true) val id : Int  = 0,
    val date : String,
    val coinsCount : Int,
    val starCount : Int,
    val time : Int,
    val typeId : Int
)
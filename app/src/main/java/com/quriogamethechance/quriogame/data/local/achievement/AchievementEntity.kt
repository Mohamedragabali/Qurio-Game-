package com.quriogamethechance.quriogame.data.local.achievement

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ACHIEVEMENT_TABLE")
data class AchievementEntity(
    @PrimaryKey(autoGenerate = true) val id : Int  = 0,
    val name : String,
    val nickname : String,
    val isOpen: Boolean,
    val openImage:Int,
    val closeImage:Int,
    val description : String,
    val howToGetIt :String ,
)
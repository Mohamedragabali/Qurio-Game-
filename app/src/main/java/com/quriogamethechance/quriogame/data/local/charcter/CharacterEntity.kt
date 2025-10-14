package com.quriogamethechance.quriogame.data.local.charcter

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CHARACTER_TABLE")
data class CharacterEntity(
    @PrimaryKey(autoGenerate = true) val id : Int  = 0,
    val name : String,
    val description : String,
    val age : String,
    val price : Int,
    val isOpen: Boolean,
    val isSelected: Boolean,
    val openImage:Int,
    val closeImage:Int,
    val characterImage:Int
)
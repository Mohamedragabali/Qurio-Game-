package com.quriogamethechance.quriogame.presenter.charcter

data class Character(
    val id : Int = 0 ,
    val name : String,
    val description : String,
    val age : String,
    val price : Int,
    val isOpen: Boolean,
    val isSelected : Boolean,
    val openImage:Int,
    val closeImage:Int,
    val characterImage:Int
)

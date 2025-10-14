package com.quriogamethechance.quriogame.presenter.charcter

data class Character(
    val name : String,
    val description : String,
    val age : String,
    val price : Int,
    val isOpen: Boolean,
    val openImage:Int,
    val closeImage:Int,
    val characterImage:Int
)

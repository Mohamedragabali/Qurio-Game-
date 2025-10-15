package com.quriogamethechance.quriogame.presenter.achievement


data class Achievement(
    val id : Int  ,
    val name : String,
    val nickname : String,
    val isOpen: Boolean,
    val openImage:Int,
    val closeImage:Int,
    val description : String,
    val howToGetIt :String ,
)

package com.quriogamethechance.quriogame.data

import com.quriogamethechance.quriogame.ui.game_fragment.Question

interface Repository {
    fun getGameQuestion():List<Question>
}
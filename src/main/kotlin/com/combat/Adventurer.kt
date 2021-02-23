package com.combat

class Adventurer(val range: RANGE = RANGE.MELEE, val position: Position = Position(0,0)) {

    var health: Int = TOTAL_HEALTH
    var level: Int = STARTER_LEVEL

    companion object {
        const val TOTAL_HEALTH = 1000
        const val STARTER_LEVEL = 1
    }

    fun isAlive(): Boolean = health > 0
}


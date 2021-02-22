package com.combat

class Adventurer(var health: Int = 1000, var level: Int = 1) {

    companion object {
        const val TOTAL_HEALTH = 1000
        const val STARTER_LEVEL = 1
        fun instance(): Adventurer {
            return Adventurer(TOTAL_HEALTH, STARTER_LEVEL)
        }
    }

    fun isAlive(): Boolean = health > 0

}

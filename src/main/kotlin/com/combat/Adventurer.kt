package com.combat

class Adventurer(var health: Int = 1000, var level: Int = 1, val range: RANGE) {

    companion object {
        const val TOTAL_HEALTH = 1000
        const val STARTER_LEVEL = 1
        fun instance(range: RANGE = RANGE.MELEE): Adventurer {
            return Adventurer(TOTAL_HEALTH, STARTER_LEVEL, range)
        }
    }

    fun isAlive(): Boolean = health > 0

}

enum class RANGE(val range: Int) {
    MELEE(2),
    RANGED(20)
}
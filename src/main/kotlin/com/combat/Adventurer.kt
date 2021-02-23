package com.combat

class Adventurer(var health: Int = 1000, var level: Int = 1, val range: RANGE, val position: Position) {

    companion object {
        const val TOTAL_HEALTH = 1000
        const val STARTER_LEVEL = 1
        fun instance(range: RANGE = RANGE.MELEE, position: Position = Position(0,0)): Adventurer {
            return Adventurer(TOTAL_HEALTH, STARTER_LEVEL, range, position)
        }
    }

    fun isAlive(): Boolean = health > 0

}

data class Position(val colIndex: Int, val rowIndex: Int)

enum class RANGE(val value: Int) {
    MELEE(2),
    RANGED(20)
}
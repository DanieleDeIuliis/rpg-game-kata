package com.combat

class Adventurer(val range: RANGE, val position: Position) {

    var health: Int = TOTAL_HEALTH
    var level: Int = STARTER_LEVEL
        private set

    companion object {
        const val TOTAL_HEALTH = 1000
        const val STARTER_LEVEL = 1
        fun instance(range: RANGE = RANGE.MELEE, position: Position = Position(0,0)): Adventurer {
            return Adventurer(range, position)
        }
    }

    fun isAlive(): Boolean = health > 0

    fun increaseLevelBy(levelUp: Int) {
        level += levelUp
    }

}

data class Position(val colIndex: Int, val rowIndex: Int)

enum class RANGE(val value: Int) {
    MELEE(2),
    RANGED(20)
}
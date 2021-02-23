package com.combat

class Adventurer(
    val range: RANGE = RANGE.MELEE,
    val position: Position = Position(0, 0),
    private val factions: MutableList<Faction> = mutableListOf()
) {

    var health: Int = TOTAL_HEALTH
    var level: Int = STARTER_LEVEL

    companion object {
        const val TOTAL_HEALTH = 1000
        const val STARTER_LEVEL = 1
    }

    fun isAlive(): Boolean = health > 0

    fun join(faction: Faction) {
        if(factions.contains(faction)) {
            return
        }

        faction.join(this)
        factions.add(faction)
    }

    fun leave(faction: Faction) {
        if(factions.contains(faction)) {
            faction.remove(this)
            factions.remove(faction)
        }
    }
}


package com.combat

class Adventurer(
    val range: Range = Range.MELEE,
    override val position: Position = Position(0, 0)
): TargetableEntity {

    override var health: Int = TOTAL_HEALTH
    override var level: Int = STARTER_LEVEL
    private val factions: MutableSet<Faction> = mutableSetOf()

    companion object {
        const val TOTAL_HEALTH = 1000
        const val STARTER_LEVEL = 1
    }

    fun isAlive(): Boolean = health > 0

    fun join(faction: Faction) {
        if(factions.add(faction)) {
            faction.join(this)
        }
    }

    fun leave(faction: Faction) {
        if(factions.remove(faction)) {
            faction.remove(this)
        }
    }

    fun getFactions(): Set<Faction> {
        return factions
    }
}


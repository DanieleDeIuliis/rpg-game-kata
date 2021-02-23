package com.combat

class ActionChecker(private val positionChecker: PositionChecker) {
    fun canDamage(firstAdventurer: Adventurer, secondAdventurer: Adventurer, damageAmount: Int): Boolean {
        if(firstAdventurer === secondAdventurer) {
            throw IllegalCombatAction()
        }
        return damageAmount > 0 && positionChecker.areInRange(firstAdventurer, secondAdventurer)
    }

    fun canHeal(adventurer: Adventurer, healAmount: Int) = adventurer.isAlive() && healAmount >= 0

}

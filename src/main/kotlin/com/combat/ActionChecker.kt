package com.combat

class ActionChecker(private val positionChecker: PositionChecker, val alliesChecker: AlliesChecker) {
    fun canDamage(firstAdventurer: Adventurer, secondAdventurer: Adventurer, damageAmount: Int): Boolean {
        if(firstAdventurer === secondAdventurer) {
            throw IllegalCombatAction()
        }
        return damageAmount > 0 && positionChecker.areInRange(firstAdventurer, secondAdventurer) && !alliesChecker.areAllies(firstAdventurer, secondAdventurer)
    }

    fun canHeal(adventurer: Adventurer, healAmount: Int) = adventurer.isAlive() && healAmount >= 0

}

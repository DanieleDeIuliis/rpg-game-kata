package com.combat

class ActionChecker(private val positionChecker: PositionChecker, private val alliesChecker: AlliesChecker) {
    fun canDamage(firstAdventurer: Adventurer, secondAdventurer: TargetableEntity, damageAmount: Int): Boolean {
        if(firstAdventurer === secondAdventurer) {
            throw IllegalCombatAction()
        }
        return damageAmount > 0 && positionChecker.areInRange(firstAdventurer, secondAdventurer) && !alliesChecker.areAllies(firstAdventurer, secondAdventurer)
    }

    fun canHeal(adventurer: Adventurer, healAmount: Int) = adventurer.isAlive() && healAmount >= 0

    fun canHeal(firstAdventurer: Adventurer, secondAdventurer: Adventurer, healAmount: Int): Boolean {
        if(alliesChecker.areAllies(firstAdventurer, secondAdventurer)) {
            return canHeal(secondAdventurer, healAmount)
        }
        return false
    }
}

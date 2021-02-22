package com.combat

class ActionChecker {
    fun canDamage(firstAdventurer: Adventurer, secondAdventurer: Adventurer, damageAmount: Int): Boolean {
        if(firstAdventurer === secondAdventurer) {
            throw IllegalCombatAction()
        }
        return damageAmount > 0
    }

    fun canHeal(adventurer: Adventurer, healAmount: Int) = adventurer.isAlive() && healAmount >= 0

}

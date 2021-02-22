package com.combat

class CombatManager(private val actionChecker: ActionChecker) {

    fun damage(firstAdventurer: Adventurer, damagedAdventurer: Adventurer, damageAmount: Int) {
        if(actionChecker.canDamage(firstAdventurer, damagedAdventurer, damageAmount)) {
            inflictDamage(firstAdventurer, damagedAdventurer, damageAmount)
        }
    }

    private fun inflictDamage(firstAdventurer: Adventurer, secondAdventurer: Adventurer, damageAmount: Int) {
        val damageAfterLevelCalculation = computeDamageBasedOnLevel(firstAdventurer, secondAdventurer, damageAmount)
        secondAdventurer.health -= damageAfterLevelCalculation
        if(secondAdventurer.health < 0) {
            secondAdventurer.health = 0
        }
    }

    private fun computeDamageBasedOnLevel(
        firstAdventurer: Adventurer,
        secondAdventurer: Adventurer,
        damageAmount: Int
    ): Int {
        var damage = damageAmount
        if(firstAdventurer.level - secondAdventurer.level >= 5) {
            damage *= 2
        }
        if(secondAdventurer.level - firstAdventurer.level >= 5) {
            damage /= 2
        }
        return damage
    }

    fun heal(adventurer: Adventurer, healAmount: Int) {
        if(actionChecker.canHeal(adventurer, healAmount)) {
            applyHeal(adventurer, healAmount)
        }
    }

    private fun applyHeal(adventurer: Adventurer, healAmount: Int) {
        adventurer.health += healAmount
        if(adventurer.health > Adventurer.TOTAL_HEALTH) {
            adventurer.health = Adventurer.TOTAL_HEALTH
        }
    }
}
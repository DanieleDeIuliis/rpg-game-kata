package com.combat

class CombatManager(private val actionChecker: ActionChecker, private val damageCalculator: DamageCalculator) {

    fun damage(firstAdventurer: Adventurer, damagedAdventurer: Adventurer, damageAmount: Int) {
        if(actionChecker.canDamage(firstAdventurer, damagedAdventurer, damageAmount)) {
            inflictDamage(firstAdventurer, damagedAdventurer, damageAmount)
        }
    }

    private fun inflictDamage(firstAdventurer: Adventurer, secondAdventurer: Adventurer, damageAmount: Int) {
        val damageAfterLevelCalculation = damageCalculator.computeDamageBasedOnLevel(firstAdventurer, secondAdventurer, damageAmount)
        secondAdventurer.health -= damageAfterLevelCalculation
        if(secondAdventurer.health < 0) {
            secondAdventurer.health = 0
        }
    }

    fun heal(adventurer: Adventurer, healAmount: Int) {
        if(actionChecker.canHeal(adventurer, healAmount)) {
            applyHeal(adventurer, healAmount)
        }
    }

    fun heal(adventurer: Adventurer, toHeal: Adventurer, healAmount: Int) {
        if(actionChecker.canHeal(adventurer, toHeal, healAmount)) {
            applyHeal(toHeal, healAmount)
        }
    }

    private fun applyHeal(adventurer: Adventurer, healAmount: Int) {
        adventurer.health += healAmount
        if(adventurer.health > Adventurer.TOTAL_HEALTH) {
            adventurer.health = Adventurer.TOTAL_HEALTH
        }
    }
}
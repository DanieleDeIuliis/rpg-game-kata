package com.combat

class CombatManager() {

    fun damage(firstAdventurer: Adventurer, damagedAdventurer: Adventurer, damageAmount: Int) {
        if(canDamageOrThrow(firstAdventurer, damagedAdventurer, damageAmount)) {
            inflictDamage(damageAmount, damagedAdventurer)
        }
    }

    private fun canDamageOrThrow(firstAdventurer: Adventurer, damagedAdventurer: Adventurer, damageAmount: Int): Boolean {
        if(firstAdventurer === damagedAdventurer) {
            throw IllegalCombatAction()
        }
        return damageAmount > 0
    }

    private fun inflictDamage(damageAmount: Int, damagedAdventurer: Adventurer) {
        damagedAdventurer.health -= damageAmount
        if(damagedAdventurer.health < 0) {
            damagedAdventurer.health = 0
        }
    }

    fun heal(adventurer: Adventurer, healAmount: Int) {
        if(canHeal(adventurer, healAmount)) {
            applyHeal(adventurer, healAmount)
        }
    }

    private fun applyHeal(adventurer: Adventurer, healAmount: Int) {
        adventurer.health += healAmount
        if(adventurer.health > Adventurer.TOTAL_HEALTH) {
            adventurer.health = Adventurer.TOTAL_HEALTH
        }
    }

    private fun canHeal(adventurer: Adventurer, healAmount: Int) = adventurer.isAlive() && healAmount >= 0
}
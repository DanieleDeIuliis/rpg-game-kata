package com.combat

class Adventurer(var health: Int = 1000, val level: Int = 1) {

    val TOTAL_HEALTH = 1000

    fun isAlive(): Boolean = health > 0

    fun damage(damagedAdventurer: Adventurer, damageAmount: Int) {
        if(damageAmount > 0) {
            inflictDamage(damageAmount, damagedAdventurer)
        }
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
        if(adventurer.health > TOTAL_HEALTH) {
            adventurer.health = TOTAL_HEALTH
        }
    }

    private fun canHeal(adventurer: Adventurer, healAmount: Int) = adventurer.isAlive() && healAmount >= 0

}

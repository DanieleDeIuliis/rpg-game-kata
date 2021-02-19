package com.combat

class Adventurer(var health: Int = 1000, val level: Int = 1) {

    val TOTAL_HEALTH = 1000

    fun isAlive(): Boolean = health > 0

    fun damage(damagedAdventurer: Adventurer, damageAmount: Int) {
        if(damageAmount > 0) {
            if(damageAmount >= damagedAdventurer.health) {
                damagedAdventurer.health = 0
                return
            }
            damagedAdventurer.health -= damageAmount
        }
    }

    fun heal(adventurer: Adventurer, healAmount: Int) {
        if(adventurer.isAlive() && healAmount >= 0) {
            if((adventurer.health + healAmount) > TOTAL_HEALTH) {
                adventurer.health = TOTAL_HEALTH
                return
            }
            adventurer.health += healAmount
        }

    }

}

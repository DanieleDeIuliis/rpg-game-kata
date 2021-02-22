package com.combat

class DamageCalculator {

    fun computeDamageBasedOnLevel(
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
}

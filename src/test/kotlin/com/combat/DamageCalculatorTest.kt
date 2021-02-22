package com.combat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class DamageCalculatorTest {
    @Test
    fun `when an adventurer is 5 level higher then a second one, the damage is 50% increased`() {
        val firstAdventurer = Adventurer(level = 6)
        val secondAdventurer = Adventurer.instance()

        val damage = DamageCalculator().computeDamageBasedOnLevel(firstAdventurer, secondAdventurer, 200)

        assertThat(damage).isEqualTo(400)
    }

    @Test
    fun `when an adventurer is 5 level lower then a second one, the damage is reduced by 50%`() {
        val firstAdventurer = Adventurer.instance()
        val secondAdventurer = Adventurer(level = 6)

        val damage = DamageCalculator().computeDamageBasedOnLevel(firstAdventurer, secondAdventurer, 200)

        assertThat(damage).isEqualTo(100)
    }
}
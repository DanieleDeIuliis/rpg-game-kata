package com.combat

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFalse

class ActionCheckerTest {

    @Test
    fun `an adventurer can't damage himself`() {
        val adventurer = Adventurer.instance()

        assertThrows<IllegalCombatAction> { ActionChecker().canDamage(adventurer, adventurer, 100) }
    }

    @Test
    fun `an adventurer can't inflict a negative damage`() {
        val firstAdventurer = Adventurer.instance()
        val secondAdventurer = Adventurer.instance()

        val canDamage = ActionChecker().canDamage(firstAdventurer, secondAdventurer, -100)

        assertFalse(canDamage)
    }

    @Test
    fun `an adventurer can't heal a dead Adventurer'`() {
        val adventurer = Adventurer(health = 0)

        assertFalse(ActionChecker().canHeal(adventurer, 200))
    }

    @Test
    fun `an adventurer can't heal a negative damage`() {
        val adventurer = Adventurer.instance()

        assertFalse(ActionChecker().canHeal(adventurer, -200))
    }

}
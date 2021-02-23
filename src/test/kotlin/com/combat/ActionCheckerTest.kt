package com.combat

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFalse

class ActionCheckerTest {

    private val positionChecker: PositionChecker = mockk()

    @Test
    fun `an adventurer can't damage himself`() {
        val adventurer = Adventurer.instance()
        every { positionChecker.areInRange(adventurer, adventurer) } returns true

        assertThrows<IllegalCombatAction> { ActionChecker(positionChecker).canDamage(adventurer, adventurer, 100) }
    }

    @Test
    fun `an adventurer can't inflict a negative damage`() {
        val firstAdventurer = Adventurer.instance()
        val secondAdventurer = Adventurer.instance()
        every { positionChecker.areInRange(firstAdventurer, secondAdventurer) } returns true

        val canDamage = ActionChecker(positionChecker).canDamage(firstAdventurer, secondAdventurer, -100)

        assertFalse(canDamage)
    }

    @Test
    fun `an adventurer can't damage a target not in range`() {
        val firstAdventurer = Adventurer.instance()
        val secondAdventurer = Adventurer.instance()
        every { positionChecker.areInRange(firstAdventurer, secondAdventurer) } returns false

        val canDamage = ActionChecker(positionChecker).canDamage(firstAdventurer, secondAdventurer, -100)

        assertFalse(canDamage)
    }

    @Test
    fun `an adventurer can't heal a dead Adventurer'`() {
        val adventurer = getADeadAdventurer()

        assertFalse(ActionChecker(positionChecker).canHeal(adventurer, 200))
    }

    @Test
    fun `an adventurer can't heal a negative damage`() {
        val adventurer = Adventurer.instance()

        assertFalse(ActionChecker(positionChecker).canHeal(adventurer, -200))
    }

    private fun getADeadAdventurer(): Adventurer {
        val adventurer = Adventurer.instance()
        val actionChecker: ActionChecker = mockk()
        val damageCalculator: DamageCalculator = mockk()
        every { actionChecker.canDamage(any(), any(), any()) } returns true
        every { damageCalculator.computeDamageBasedOnLevel(any(), any(), any()) } returns 1000

        CombatManager(actionChecker, damageCalculator).damage(adventurer, adventurer, 1000)
        return adventurer
    }

}
package com.combat

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class ActionCheckerTest {

    private val positionChecker: PositionChecker = mockk()
    private val alliesChecker: AlliesChecker = mockk()

    @Test
    fun `an adventurer can't damage himself`() {
        val adventurer = Adventurer()
        every { positionChecker.areInRange(adventurer, adventurer) } returns true

        assertThrows<IllegalCombatAction> { ActionChecker(positionChecker, alliesChecker).canDamage(adventurer, adventurer, 100) }
    }

    @Test
    fun `an adventurer can't inflict a negative damage`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer()
        every { positionChecker.areInRange(firstAdventurer, secondAdventurer) } returns true

        val canDamage = ActionChecker(positionChecker, alliesChecker).canDamage(firstAdventurer, secondAdventurer, -100)

        assertFalse(canDamage)
    }

    @Test
    fun `an adventurer can't damage a target not in range`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer()
        every { positionChecker.areInRange(firstAdventurer, secondAdventurer) } returns false

        val canDamage = ActionChecker(positionChecker, alliesChecker).canDamage(firstAdventurer, secondAdventurer, -100)

        assertFalse(canDamage)
    }

    @Test
    fun `an adventurer can't damage an ally`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer()
        every { positionChecker.areInRange(firstAdventurer, secondAdventurer) } returns true
        every { alliesChecker.areAllies(firstAdventurer, secondAdventurer) } returns true

        val canDamage = ActionChecker(positionChecker, alliesChecker).canDamage(firstAdventurer, secondAdventurer, 100)

        assertFalse(canDamage)
    }

    @Test
    fun `an adventurer can't heal a dead Adventurer'`() {
        val adventurer = getADeadAdventurer()

        assertFalse(ActionChecker(positionChecker, alliesChecker).canHeal(adventurer, 200))
    }

    @Test
    fun `an adventurer can't heal a negative damage`() {
        val adventurer = Adventurer()

        assertFalse(ActionChecker(positionChecker, alliesChecker).canHeal(adventurer, -200))
    }

    @Test
    fun `an adventurer can heal an ally`() {
        val faction: Faction = mockk(relaxed = true)
        val firstAdventurer = Adventurer()
        firstAdventurer.join(faction)
        val secondAdventurer = Adventurer()
        secondAdventurer.join(faction)
        every { alliesChecker.areAllies(firstAdventurer, secondAdventurer) } returns true

        assertTrue(ActionChecker(positionChecker, alliesChecker).canHeal(firstAdventurer, secondAdventurer, 200))
    }

    @Test
    fun `an adventurer can not heal a non ally`() {
        val faction: Faction = mockk(relaxed = true)
        val firstAdventurer = Adventurer()
        firstAdventurer.join(faction)
        val secondAdventurer = Adventurer()
        secondAdventurer.join(faction)
        every { alliesChecker.areAllies(firstAdventurer, secondAdventurer) } returns false

        assertFalse(ActionChecker(positionChecker, alliesChecker).canHeal(firstAdventurer, secondAdventurer, 200))
    }

    private fun getADeadAdventurer(): Adventurer {
        val adventurer = Adventurer()
        val actionChecker: ActionChecker = mockk()
        val damageCalculator: DamageCalculator = mockk()
        every { actionChecker.canDamage(any(), any(), any()) } returns true
        every { damageCalculator.computeDamageBasedOnLevel(any(), any(), any()) } returns 1000

        CombatManager(actionChecker, damageCalculator).damage(adventurer, adventurer, 1000)
        return adventurer
    }

}
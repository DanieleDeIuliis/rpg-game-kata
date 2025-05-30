package com.combat

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class CombatManagerTest {

    private val actionChecker: ActionChecker = mockk()
    private val damageCalculator: DamageCalculator = mockk()


    @Test
    fun `when can deal damage, an adventurer damages another one`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer()
        every { actionChecker.canDamage(firstAdventurer, secondAdventurer, 200) } returns true
        every { damageCalculator.computeDamageBasedOnLevel(firstAdventurer, secondAdventurer, 200) } returns 200

        CombatManager(actionChecker, damageCalculator).damage(firstAdventurer, secondAdventurer, 200)

        assertThat(secondAdventurer.isAlive()).isEqualTo(true)
        assertThat(secondAdventurer.health).isEqualTo(800)
    }

    @Test
    fun `when can't deal damage, an adventurer does not damage another one`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer()
        every { actionChecker.canDamage(firstAdventurer, secondAdventurer, -200) } returns false

        CombatManager(actionChecker, damageCalculator).damage(firstAdventurer, secondAdventurer, -200)

        assertThat(secondAdventurer.isAlive()).isEqualTo(true)
        assertThat(secondAdventurer.health).isEqualTo(1000)
    }

    @Test
    fun `when an adventurer gets a damage greater than its health, it dies with health equals to zero`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer()
        every { actionChecker.canDamage(firstAdventurer, secondAdventurer, 2000) } returns true
        every { damageCalculator.computeDamageBasedOnLevel(firstAdventurer, secondAdventurer, 2000) } returns 2000

        CombatManager(actionChecker, damageCalculator).damage(firstAdventurer, secondAdventurer, 2000)

        assertThat(secondAdventurer.isAlive()).isEqualTo(false)
        assertThat(secondAdventurer.health).isEqualTo(0)
    }

    @Test
    fun `an adventurer can damage an item`() {
        val firstAdventurer = Adventurer()
        val item = Item(health = 1500)
        every { actionChecker.canDamage(firstAdventurer, item, 200) } returns true
        every { damageCalculator.computeDamageBasedOnLevel(firstAdventurer, item, 200) } returns 200

        CombatManager(actionChecker, damageCalculator).damage(firstAdventurer, item, 200)

        assertThat(item.health).isEqualTo(1300)
    }

    @Test
    fun `an item that receives a damage greater than its health is destroyed`() {
        val firstAdventurer = Adventurer()
        val item = Item(health = 1500)
        every { actionChecker.canDamage(firstAdventurer, item, 2000) } returns true
        every { damageCalculator.computeDamageBasedOnLevel(firstAdventurer, item, 2000) } returns 2000

        CombatManager(actionChecker, damageCalculator).damage(firstAdventurer, item, 2000)

        assertTrue(item.isDestroyed())
        assertThat(item.health).isEqualTo(0)
    }

    @Test
    fun `an adventurer that heals restore the target health'`() {
        val adventurer = getADamagedAdventurer(500)
        every { actionChecker.canHeal(adventurer, 100) } returns true

        CombatManager(actionChecker, damageCalculator).heal(adventurer, 100)

        assertThat(adventurer.isAlive()).isEqualTo(true)
        assertThat(adventurer.health).isEqualTo(600)
    }

    @Test
    fun `an adventurer that heals can't restore health to a value grater than the maximum amount`() {
        val adventurer = getADamagedAdventurer(100)
        every { actionChecker.canHeal(adventurer, 200) } returns true

        CombatManager(actionChecker, damageCalculator).heal(adventurer, 200)

        assertThat(adventurer.isAlive()).isEqualTo(true)
        assertThat(adventurer.health).isEqualTo(1000)
    }

    @Test
    fun `an adventurer can heal another target that is its ally`() {
        val faction: Faction = mockk(relaxed = true)
        val adventurer = getADamagedAdventurer(100)
        val ally = Adventurer()
        adventurer.join(faction)
        ally.join(faction)

        every { actionChecker.canHeal(ally, adventurer, 200) } returns true

        CombatManager(actionChecker, damageCalculator).heal(ally, adventurer, 200)

        assertThat(adventurer.isAlive()).isEqualTo(true)
        assertThat(adventurer.health).isEqualTo(1000)
    }

    private fun getADamagedAdventurer(damageAmount: Int): Adventurer {
        val adventurer = Adventurer()
        every { actionChecker.canDamage(any(), any(), any()) } returns true
        every { damageCalculator.computeDamageBasedOnLevel(any(), any(), any()) } returns damageAmount
        CombatManager(actionChecker, damageCalculator).damage(adventurer, adventurer, damageAmount)
        return adventurer
    }
}
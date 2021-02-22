package com.combat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class CombatManagerTest {

    @Test
    fun `creates and adventurer with default values`() {
        val adventurer = Adventurer.instance()

        assertThat(adventurer.health).isEqualTo(1000)
        assertThat(adventurer.level).isEqualTo(1)
        assertThat(adventurer.isAlive()).isEqualTo(true)
    }

    @Test
    fun `an adventurer can't inflict a negative damage`() {
        val firstAdventurer = Adventurer.instance()
        val secondAdventurer = Adventurer.instance()

        CombatManager().damage(firstAdventurer, secondAdventurer, -100)

        assertThat(secondAdventurer.health).isEqualTo(1000)
    }

    @Test
    fun `an adventurer can damage another one`() {
        val firstAdventurer = Adventurer.instance()
        val secondAdventurer = Adventurer.instance()

        CombatManager().damage(firstAdventurer, secondAdventurer, 100)

        assertThat(secondAdventurer.health).isEqualTo(900)
    }

    @Test
    fun `an adventurer can't damage himself`() {
        val adventurer = Adventurer.instance()

        assertThrows<IllegalCombatAction> { CombatManager().damage(adventurer, adventurer, 200) }
    }

    @Test
    fun `when an adventurer gets a damage greater than its health, it dies with health equals to zero`() {
        val firstAdventurer = Adventurer.instance()
        val secondAdventurer = Adventurer.instance()

        CombatManager().damage(firstAdventurer, secondAdventurer, 2000)

        assertThat(secondAdventurer.isAlive()).isEqualTo(false)
        assertThat(secondAdventurer.health).isEqualTo(0)
    }

    @Test
    fun `when an adventurer is 5 level higher then a second one, the damage is 50% increased`() {
        val firstAdventurer = Adventurer(level = 6)
        val secondAdventurer = Adventurer.instance()

        CombatManager().damage(firstAdventurer, secondAdventurer, 200)

        assertThat(secondAdventurer.isAlive()).isEqualTo(true)
        assertThat(secondAdventurer.health).isEqualTo(600)
    }

    @Test
    fun `an adventurer can't heal a dead Adventurer'`() {
        val adventurer = Adventurer(health = 0)

        CombatManager().heal(adventurer, 100)

        assertThat(adventurer.isAlive()).isEqualTo(false)
        assertThat(adventurer.health).isEqualTo(0)
    }

    @Test
    fun `an adventurer can't heal a negative damage`() {
        val adventurer = Adventurer.instance()

        CombatManager().heal(adventurer, -100)

        assertThat(adventurer.health).isEqualTo(1000)
    }

    @Test
    fun `an adventurer that heals can't restore health to a value grater than the maximum amount'`() {
        val adventurer = Adventurer(health = 900)

        CombatManager().heal(adventurer, 200)

        assertThat(adventurer.isAlive()).isEqualTo(true)
        assertThat(adventurer.health).isEqualTo(1000)
    }

    @Test
    fun `an adventurer that heals restore the target health'`() {
        val adventurer = Adventurer(health = 500)

        CombatManager().heal(adventurer, 100)

        assertThat(adventurer.isAlive()).isEqualTo(true)
        assertThat(adventurer.health).isEqualTo(600)
    }
}
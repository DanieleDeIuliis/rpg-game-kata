package com.combat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

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
        val secondAdventurer = Adventurer.instance()

        CombatManager().damage(secondAdventurer, -100)

        assertThat(secondAdventurer.health).isEqualTo(1000)
    }

    @Test
    fun `an adventurer can damage another one`() {
        val secondAdventurer = Adventurer.instance()

        CombatManager().damage(secondAdventurer, 100)

        assertThat(secondAdventurer.health).isEqualTo(900)
    }

    @Test
    fun `when an adventurer gets a damage greater than its health, it dies with health equals to zero`() {
        val secondAdventurer = Adventurer.instance()

        CombatManager().damage(secondAdventurer, 2000)

        assertThat(secondAdventurer.isAlive()).isEqualTo(false)
        assertThat(secondAdventurer.health).isEqualTo(0)
    }

    @Test
    fun `an adventurer can't heal a dead Adventurer'`() {
        val secondAdventurer = Adventurer(health = 0)

        CombatManager().heal(secondAdventurer, 100)

        assertThat(secondAdventurer.isAlive()).isEqualTo(false)
        assertThat(secondAdventurer.health).isEqualTo(0)
    }

    @Test
    fun `an adventurer can't heal a negative damage`() {
        val secondAdventurer = Adventurer.instance()

        CombatManager().heal(secondAdventurer, -100)

        assertThat(secondAdventurer.health).isEqualTo(1000)
    }

    @Test
    fun `an adventurer that heals can't restore health to a value grater than the maximum amount'`() {
        val secondAdventurer = Adventurer(health = 900)

        CombatManager().heal(secondAdventurer, 200)

        assertThat(secondAdventurer.isAlive()).isEqualTo(true)
        assertThat(secondAdventurer.health).isEqualTo(1000)
    }

    @Test
    fun `an adventurer that heals restore the target health'`() {
        val secondAdventurer = Adventurer(health = 500)

        CombatManager().heal(secondAdventurer, 100)

        assertThat(secondAdventurer.isAlive()).isEqualTo(true)
        assertThat(secondAdventurer.health).isEqualTo(600)
    }
}
package com.combat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class AdventurerTest {
    @Test
    fun `when an adventurer is created, it is alive with the default health and level`() {
        val adventurer = Adventurer()

        assertThat(adventurer.health).isEqualTo(1000)
        assertThat(adventurer.level).isEqualTo(1)
        assertThat(adventurer.isAlive()).isEqualTo(true)
    }

    @Test
    fun `an adventurer can't inflict a negative damage`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer()

        firstAdventurer.damage(secondAdventurer, -100)

        assertThat(secondAdventurer.health).isEqualTo(1000)
    }

    @Test
    fun `an adventurer can damage another one`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer()

        firstAdventurer.damage(secondAdventurer, 100)

        assertThat(secondAdventurer.health).isEqualTo(900)
    }

    @Test
    fun `when an adventurer gets a damage greater than its health, it dies with health equals to zero`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer()

        firstAdventurer.damage(secondAdventurer, 2000)

        assertThat(secondAdventurer.isAlive()).isEqualTo(false)
        assertThat(secondAdventurer.health).isEqualTo(0)
    }

    @Test
    fun `an adventurer can't heal a dead Adventurer'`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer(health = 0)

        firstAdventurer.heal(secondAdventurer, 100)

        assertThat(secondAdventurer.isAlive()).isEqualTo(false)
        assertThat(secondAdventurer.health).isEqualTo(0)
    }

    @Test
    fun `an adventurer can't heal a negative damage`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer()

        firstAdventurer.heal(secondAdventurer, -100)

        assertThat(secondAdventurer.health).isEqualTo(1000)
    }

    @Test
    fun `an adventurer that heals can't restore health to a value grater than the maximum amount'`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer(health = 900)

        firstAdventurer.heal(secondAdventurer, 200)

        assertThat(secondAdventurer.isAlive()).isEqualTo(true)
        assertThat(secondAdventurer.health).isEqualTo(1000)
    }

    @Test
    fun `an adventurer that heals restore the target health'`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer(health = 500)

        firstAdventurer.heal(secondAdventurer, 100)

        assertThat(secondAdventurer.isAlive()).isEqualTo(true)
        assertThat(secondAdventurer.health).isEqualTo(600)
    }
}
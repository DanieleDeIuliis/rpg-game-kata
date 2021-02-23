package com.combat

import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue


class AdventurerTest {
    @Test
    fun `an adventurer is created with no Factions assigned`() {
        val adventurer = Adventurer()
        assertTrue(adventurer.getFactions().isEmpty())
    }

    @Test
    fun `when an adventurer joins a factions, it will be added to its factions`() {
        val adventurer = Adventurer()
        val faction: Faction = mockk(relaxed = true)

        adventurer.join(faction)

        verify { faction.join(adventurer) }
        assertThat(adventurer.getFactions().size).isEqualTo(1)
        assertTrue(adventurer.getFactions().contains(faction))
    }

    @Test
    fun `an adventurer can't join twice the same faction`() {
        val faction: Faction = mockk(relaxed = true)
        val adventurer = Adventurer()

        adventurer.join(faction)
        adventurer.join(faction)

        verify(exactly = 1) { faction.join(adventurer) }
        assertThat(adventurer.getFactions().size).isEqualTo(1)
    }

    @Test
    fun `when an adventurer leaves a factions, it will be removed from its factions`() {
        val faction: Faction = mockk(relaxed = true)
        val adventurer = Adventurer()
        adventurer.join(faction)

        adventurer.leave(faction)

        verify { faction.remove(adventurer) }
        assertTrue(adventurer.getFactions().isEmpty())
    }

    @Test
    fun `an adventurer can't leave a faction that he didn't previously join`() {
        val faction: Faction = mockk(relaxed = true)
        val adventurer = Adventurer()
        adventurer.join(Faction())

        adventurer.leave(faction)

        verify(exactly = 0) { faction.remove(adventurer) }
        assertThat(adventurer.getFactions().size).isEqualTo(1)
    }
}
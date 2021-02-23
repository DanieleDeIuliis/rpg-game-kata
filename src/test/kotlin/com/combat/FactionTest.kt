package com.combat

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class FactionTest {
    @Test
    fun `when an adventurer joins the faction, it is added to the member list`() {
        val members = mutableListOf<Adventurer>()
        val faction = Faction(members = members)
        faction.join(Adventurer())

        assertThat(members.size).isEqualTo(1)
    }

    @Test
    fun `an adventurer can't join the faction twice`() {
        val adventurer = Adventurer()
        val members = mutableListOf(adventurer)
        val faction = Faction(members = members)
        faction.join(adventurer)

        assertThat(members.size).isEqualTo(1)
    }

    @Test
    fun `when an adventurer leaves the faction, it is removed from the member list`() {
        val adventurer = Adventurer()
        val members = mutableListOf(adventurer)
        val faction = Faction(members = members)
        faction.remove(adventurer)

        assertTrue(members.isEmpty())
    }

    @Test
    fun `an adventurer can't leave the faction twice`() {
        val adventurer = Adventurer()
        val members = mutableListOf(Adventurer())
        val faction = Faction(members = members)
        faction.remove(adventurer)

        assertThat(members.size).isEqualTo(1)
    }
}
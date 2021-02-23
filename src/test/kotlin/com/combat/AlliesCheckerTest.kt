package com.combat

import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AlliesCheckerTest {

    @Test
    fun `returns true when two adventurers are members of the same faction`() {
        val faction: Faction = mockk(relaxed = true)
        val firstAdventurer = Adventurer()
        firstAdventurer.join(faction)
        val secondAdventurer = Adventurer()
        secondAdventurer.join(faction)
        every { faction.hasMember(any()) } returns true

        val alliesChecker = AlliesChecker()

        assertTrue(alliesChecker.areAllies(firstAdventurer, secondAdventurer))
    }

    @Test
    fun `returns false when two adventurers are not members of the same faction`() {
        val faction: Faction = mockk(relaxed = true)
        val firstAdventurer = Adventurer()
        firstAdventurer.join(Faction())
        firstAdventurer.join(Faction())
        val secondAdventurer = Adventurer()
        secondAdventurer.join(faction)
        every { faction.hasMember(any()) } returns true

        val alliesChecker = AlliesChecker()

        assertFalse(alliesChecker.areAllies(firstAdventurer, secondAdventurer))
    }
}
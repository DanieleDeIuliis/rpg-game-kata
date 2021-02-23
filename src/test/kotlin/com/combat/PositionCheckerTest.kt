package com.combat

import com.combat.Range.*
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class PositionCheckerTest {

    private val distanceCalculator: DistanceCalculator = mockk()

    @Test
    fun `call computeDistance when it needs to check if two characters are in range`() {
        val adventurer = Adventurer()
        every { distanceCalculator.computeDistance(adventurer.position, adventurer.position) } returns 2.14

        PositionChecker(distanceCalculator).areInRange(adventurer, adventurer)

        verify { distanceCalculator.computeDistance(adventurer.position, adventurer.position) }
    }

    @Test
    fun `when the distance between two characters is less or equal than the range of the first one, the two are in range`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer(range = RANGED)
        every { distanceCalculator.computeDistance(firstAdventurer.position, secondAdventurer.position) } returns 1.80

        assertTrue(PositionChecker(distanceCalculator).areInRange(firstAdventurer, secondAdventurer))
    }

    @Test
    fun `when the distance between two characters is bigger than the range of the first one, the two are in range`() {
        val firstAdventurer = Adventurer()
        val secondAdventurer = Adventurer(range = RANGED)
        every { distanceCalculator.computeDistance(firstAdventurer.position, secondAdventurer.position) } returns 2.02

        assertFalse(PositionChecker(distanceCalculator).areInRange(firstAdventurer, secondAdventurer))
    }
}